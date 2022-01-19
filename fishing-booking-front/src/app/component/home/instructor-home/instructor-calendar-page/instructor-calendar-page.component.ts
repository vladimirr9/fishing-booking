import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CalendarView, CalendarEvent, CalendarEventAction, CalendarEventTimesChangedEvent } from 'angular-calendar';
import { subDays, startOfDay, addDays, endOfMonth, addHours, isSameMonth, isSameDay, endOfDay } from 'date-fns';
import { labelCache } from 'ol/render/canvas';
import { Subject } from 'rxjs';
import { ReportDialogComponent } from 'src/app/component/dialog/report-dialog/report-dialog.component';
import { AvailablePeriod } from 'src/app/model/AvailablePeriod';
import { AuthService } from 'src/app/service/auth.service';
import { AvailablePeriodService } from 'src/app/service/available-period.service';
import { DateService } from 'src/app/service/date.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-instructor-calendar-page',
  templateUrl: './instructor-calendar-page.component.html',
  styleUrls: ['./instructor-calendar-page.component.scss']
})
export class InstructorCalendarPageComponent implements OnInit {

  @ViewChild('modalContent', { static: true }) modalContent!: TemplateRef<any>;

  constructor(private modal: NgbModal,
    private storageService: StorageService,
    private availablePeriodService: AvailablePeriodService,
    private reservationService: ReservationService,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getEvents();
  }


  view: CalendarView = CalendarView.Month;

  fromTime: any
  toTime: any

  private getEvents() {
    this.events = [];

    this.availablePeriodService.getPeriods(this.storageService.getUsername()).subscribe((data: AvailablePeriod[]) => {
      data.forEach((element) => {
        let id = element.id || 0;
        this.addAvailablePeriodToCalendar(id, element.fromTime, element.toTime);
      });
    });

    this.reservationService.getReservations({ ownerEmail: this.storageService.getUsername() }).subscribe((data: any) => {
      data.forEach((element: any) => {
        let id = element.id || 0;
        this.addReservationToCalendar(id, element.startDate, element.endDate, element.reportPresent);
      });
    });
  }

  addAvailablePeriod() {
    let availablePeriod = {
      fromTime: this.fromTime,
      toTime: this.toTime,
      email: this.storageService.getUsername()
    }
    this.availablePeriodService.postPeriodInstructor(availablePeriod).subscribe((data: AvailablePeriod) => {
       this.getEvents()
    })
  }



  CalendarView = CalendarView;

  viewDate: Date = new Date();

  modalData!: {
    action: string;
    event: CalendarEvent;
  };

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fas fa-fw fa-pencil-alt"></i>',
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      },
    },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];

  refresh = new Subject<void>();

  events: CalendarEvent[] = [];

  activeDayIsOpen: boolean = true;



  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }

  addEvent(): void {
    this.events = [
      ...this.events,
      {
        title: 'New event',
        start: startOfDay(new Date()),
        end: endOfDay(new Date()),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  addAvailablePeriodToCalendar(id: number, fromTime: any, toTime: any): void {
    this.events = [
      ...this.events,
      {
        title: 'Available Period',
        start: new Date(Date.parse(fromTime)),
        end: new Date(Date.parse(toTime)),
        color: colors.blue,
        draggable: false,
        resizable: {
          beforeStart: false,
          afterEnd: false,
        },
        actions: [
          {
            label: '<i class="fas fa-fw fa-trash-alt"></i>',
            onClick: ({ event }: { event: CalendarEvent }): void => {
              this.availablePeriodService.deletePeriod(id).subscribe((data) => {
                this.events = this.events.filter((iEvent) => iEvent !== event);
              })
            },
          },
        ]
      },
    ];
  }
  addReservationToCalendar(id: any, fromTime: any, toTime: any, reportPresent: any) {
    let action: any
    if (!reportPresent && (new Date(Date.parse(toTime))) > (new Date())) {
      action = {
        label: '<i class="fas fa-edit"></i>',
        onClick: ({ event }: { event: CalendarEvent }): void => {
          const dialogConfig = new MatDialogConfig();
          dialogConfig.disableClose = true
          dialogConfig.autoFocus = true

          const dialogRef = this.dialog.open(ReportDialogComponent, dialogConfig);

          dialogRef.afterClosed().subscribe(
            res => {
              if (res) {
                this.reservationService.putReport(id, res).subscribe((data: any) => {
                  window.location.reload()
                })

              }
            }

          );
        },
      }
    }
    else {
      action = {
        label: '',
        onClick: () => { void (0) }
      }
    }


    this.events = [
      ...this.events,
      {
        title: 'Reservation',
        start: new Date(Date.parse(fromTime)),
        end: new Date(Date.parse(toTime)),
        color: colors.yellow,
        draggable: false,
        resizable: {
          beforeStart: false,
          afterEnd: false,
        },
        actions: [
          action
        ]
      },
    ];

  }



  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }
}
