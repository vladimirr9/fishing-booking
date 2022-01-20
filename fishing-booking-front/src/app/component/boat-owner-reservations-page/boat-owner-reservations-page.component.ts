import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DateService } from 'src/app/service/date.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';
import { ReportDialogComponent } from '../dialog/report-dialog/report-dialog.component';
import { ViewProfileDialogComponent } from '../dialog/view-profile-dialog/view-profile-dialog.component';

@Component({
  selector: 'app-boat-owner-reservations-page',
  templateUrl: './boat-owner-reservations-page.component.html',
  styleUrls: ['./boat-owner-reservations-page.component.scss']
})
export class BoatOwnerReservationsPageComponent implements OnInit {

  displayedColumns: string[] = ['name', 'client','startDate', 'endDate', 'price', 'report'];
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

}
