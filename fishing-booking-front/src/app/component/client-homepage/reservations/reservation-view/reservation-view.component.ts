import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ComplaintDialogComponent } from 'src/app/component/dialog/complaint-dialog/complaint-dialog.component';
import { ReviewDialogComponent } from 'src/app/component/dialog/review-dialog/review-dialog.component';
import { ReservationDTO } from 'src/app/dto/ReservationDTO';
import { ComplaintServiceService } from 'src/app/service/complaint-service.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { ReviewServiceService } from 'src/app/service/review-service.service';

@Component({
  selector: 'app-reservation-view',
  templateUrl: './reservation-view.component.html',
  styleUrls: ['./reservation-view.component.scss']
})
export class ReservationViewComponent implements OnInit {

  constructor(private reservationService: ReservationService,private dialog: MatDialog,private complaintService:ComplaintServiceService,private reviewService:ReviewServiceService) { }

  _MS_PER_DAY: number = 1000 * 60 * 60 * 24;

  @Input() reservations: ReservationDTO[] = [];

  ngOnInit(): void {
  }

  showTime(startDate: Date): string{
    return (new Date(startDate).toLocaleTimeString());
  }

 isDeletable(date: Date): boolean{
    let startDate = new Date(date)
    const utc1 = Date.UTC(startDate.getFullYear(), startDate.getMonth(), startDate.getDate());
    const utc2 = Date.UTC(new Date().getFullYear(),new Date().getMonth(), new Date().getDate());
    let result = Math.abs(Math.floor((utc2 - utc1) / this._MS_PER_DAY));
    return result >= 3;
 }

 isComplainable(date: Date):boolean{
  let startDate = new Date(date)
  const utc1 = Date.UTC(startDate.getFullYear(), startDate.getMonth(), startDate.getDate());
  const utc2 = Date.UTC(new Date().getFullYear(),new Date().getMonth(), new Date().getDate());
  let result = Math.abs(Math.floor((utc2 - utc1) / this._MS_PER_DAY));
  return result <0;
 }

  complaint(reservation: ReservationDTO):void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(ComplaintDialogComponent,dialogConfig);
    dialogRef.afterClosed().subscribe((data)=>{
      if(data){
          this.complaintService.createComplaint(reservation.id,data).subscribe(()=>{
            reservation.complaintPresent = true;
            alert("Complaint successfully sent!");
          });
      }
    });
  }

  writeReview(reservation: ReservationDTO):void{
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(ReviewDialogComponent,dialogConfig);
    dialogRef.afterClosed().subscribe((data)=>{
      if(!data) return;
      data.reservationId=reservation.id;
      this.reviewService.createReview(data).subscribe(()=>{
        reservation.reviewPresent=true;
        alert("Review successfully submited!");
      });
    });
  }

  cancelReservation(id: number):void{
    this.reservationService.deleteReservation(id).subscribe(()=>{
        alert("Reservation successfully removed!");
        this.reservations = this.reservations.filter(reservation => reservation.id != id );
    },error=>{
        alert("Removed reservation!");
    });
  }
}
