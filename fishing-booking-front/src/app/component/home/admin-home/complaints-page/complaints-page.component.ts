import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ChangeFeeDialogComponent } from 'src/app/component/dialog/change-fee-dialog/change-fee-dialog.component';
import { ComplaintDialogComponent } from 'src/app/component/dialog/complaint-dialog/complaint-dialog.component';
import { ViewComplaintDialogComponent } from 'src/app/component/dialog/view-complaint-dialog/view-complaint-dialog.component';
import { ComplaintServiceService } from 'src/app/service/complaint-service.service';

@Component({
  selector: 'app-complaints-page',
  templateUrl: './complaints-page.component.html',
  styleUrls: ['./complaints-page.component.scss']
})
export class ComplaintsPageComponent implements OnInit {

  constructor(private complaintsService: ComplaintServiceService, private dialog: MatDialog) { }

  complaints: any

  ngOnInit(): void {
    this.complaintsService.getAll().subscribe((data:any) => {
      this.complaints = data
    })
  }

  openComplaint(complaint:any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true
    dialogConfig.autoFocus = true
    dialogConfig.data = {
      clientName: `${complaint.reservation.client.firstName} ${complaint.reservation.client.lastName}`,
      startDate: complaint.reservation.startDate,
      endDate: complaint.reservation.endDate,
      price: complaint.reservation.price,
      ownerEmail: complaint.reservation.ownerEmail,
      content: complaint.content
    }
    //console.log(dialogConfig.data);

    const dialogRef = this.dialog.open(ViewComplaintDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      res => {
        if (res) {
          let id = complaint.id
          this.complaintsService.answer(id, res).subscribe((data:any) => {
            this.complaints = this.complaints.filter((comp:any) => comp != complaint);
          })
        }
      }

    );
  }

}
