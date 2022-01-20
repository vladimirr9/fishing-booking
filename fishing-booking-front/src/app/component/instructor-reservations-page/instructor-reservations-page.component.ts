import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DateService } from 'src/app/service/date.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';
import { NewReservationDialogComponent } from '../dialog/new-reservation-dialog/new-reservation-dialog.component';
import { ReportDialogComponent } from '../dialog/report-dialog/report-dialog.component';
import { ViewProfileDialogComponent } from '../dialog/view-profile-dialog/view-profile-dialog.component';

@Component({
  selector: 'app-instructor-reservations-page',
  templateUrl: './instructor-reservations-page.component.html',
  styleUrls: ['./instructor-reservations-page.component.scss']
})
export class InstructorReservationsPageComponent implements OnInit {

  displayedColumns: string[] = ['name', 'client','startDate', 'endDate', 'price', 'report', 'newReservation'];
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

  viewProfile(user : any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true
    dialogConfig.autoFocus = true

    dialogConfig.data = {email: user.client.email}
    const dialogRef = this.dialog.open(ViewProfileDialogComponent, dialogConfig);
  }


  sendReport(element: any) {
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

  
  createNewReservation(element: any) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(NewReservationDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          let reservationDto = {
            price: data.price,
            from: new Date(data.fromTime),
            to: new Date(data.toTime),
            clientUsername: element.client.email,
            entityId: element.entityId,
            type: 'ADVENTURE'
          };

          this.reservationService.createReservation(reservationDto).subscribe(()=>{
            alert("Reservation created.");
            this.reservationService.getReservations({ ownerEmail: this.storageService.getUsername() }).subscribe((data: any) => {
              this.reservations = data
            })

          },
          (error)=> {
            alert("Error.");
          });
      }
    });

    
  }

}
