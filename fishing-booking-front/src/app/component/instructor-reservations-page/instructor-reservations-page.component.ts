import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DateService } from 'src/app/service/date.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';
import { ReportDialogComponent } from '../dialog/report-dialog/report-dialog.component';

@Component({
  selector: 'app-instructor-reservations-page',
  templateUrl: './instructor-reservations-page.component.html',
  styleUrls: ['./instructor-reservations-page.component.scss']
})
export class InstructorReservationsPageComponent implements OnInit {

  displayedColumns: string[] = ['startDate', 'endDate', 'price', 'report'];
  reservations: any
  constructor(private reservationService: ReservationService,
    private dialog: MatDialog,
    private storageService: StorageService,
    public dateService: DateService) { }

  ngOnInit(): void {
    this.reservationService.getReservations({ ownerEmail: this.storageService.getUsername() }).subscribe((data: any) => {
      this.reservations = data
    })
  }


  sendReport(element: any) {
    console.log(element)
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true
    dialogConfig.autoFocus = true

    const dialogRef = this.dialog.open(ReportDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      res => {
        if (res) {
          this.reservationService.putReport(element.id, res).subscribe((data: any) => {
            this.reservationService.getReservations({ ownerEmail: this.storageService.getUsername() }).subscribe((data: any) => {
              this.reservations = data
            })
          })
        }
      }

    );
  }
}
