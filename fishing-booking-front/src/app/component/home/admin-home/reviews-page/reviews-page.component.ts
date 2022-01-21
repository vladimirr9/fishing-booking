import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ViewComplaintDialogComponent } from 'src/app/component/dialog/view-complaint-dialog/view-complaint-dialog.component';
import { ViewReviewDialogComponent } from 'src/app/component/dialog/view-review-dialog/view-review-dialog.component';
import { ReviewServiceService } from 'src/app/service/review-service.service';

@Component({
  selector: 'app-reviews-page',
  templateUrl: './reviews-page.component.html',
  styleUrls: ['./reviews-page.component.scss']
})
export class ReviewsPageComponent implements OnInit {

  constructor(private reviewsService: ReviewServiceService, private dialog: MatDialog) { }

  reviews: any

  ngOnInit(): void {
    this.reviewsService.getAllUnapproved().subscribe((data:any) => {
      this.reviews = data
    })
  }

  openReview(review:any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true
    dialogConfig.autoFocus = true
    dialogConfig.data = {
      clientName: `${review.reservation.client.firstName} ${review.reservation.client.lastName}`,
      entityName: review.reservation.entityName,
      ownerEmail: review.reservation.ownerEmail,
      comment: review.comment
    }
    console.log(dialogConfig.data);


    const dialogRef = this.dialog.open(ViewReviewDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      res => {
        if (res) {
          if (res === "approve") {
            this.reviewsService.approveReview(review.id).subscribe((data:any) => {
              this.reviews = this.reviews.filter((rev:any) => rev != review);
            })

          }
          if (res === "deny") {
            this.reviewsService.denyReview(review.id).subscribe((data:any) => {
              this.reviews = this.reviews.filter((rev:any) => rev != review);
            })
          }
        }
      }

    );
  }
}
