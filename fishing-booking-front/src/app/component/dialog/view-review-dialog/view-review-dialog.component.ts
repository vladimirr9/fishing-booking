import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DateService } from 'src/app/service/date.service';

@Component({
  selector: 'app-view-review-dialog',
  templateUrl: './view-review-dialog.component.html',
  styleUrls: ['./view-review-dialog.component.scss']
})
export class ViewReviewDialogComponent implements OnInit {


  constructor(private dialogRef: MatDialogRef<ViewReviewDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

form = new FormGroup({
  clientName: new FormControl('', Validators.required),
  entityName: new FormControl('', Validators.required),
  ownerEmail: new FormControl('', Validators.required),
  comment: new FormControl('', Validators.required)
})

ngOnInit(): void {
  this.form.controls['clientName'].setValue(this.data.clientName)
  this.form.controls['entityName'].setValue(this.data.entityName)
  this.form.controls['ownerEmail'].setValue(this.data.ownerEmail)
  this.form.controls['comment'].setValue(this.data.comment)

}

close() {
  this.dialogRef.close();
}

approve() {
  this.dialogRef.close("approve");
}
deny() {
  this.dialogRef.close("deny");
}


}
