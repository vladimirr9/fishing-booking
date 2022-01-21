import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DateService } from 'src/app/service/date.service';

@Component({
  selector: 'app-view-complaint-dialog',
  templateUrl: './view-complaint-dialog.component.html',
  styleUrls: ['./view-complaint-dialog.component.scss']
})
export class ViewComplaintDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ViewComplaintDialogComponent>,
      @Inject(MAT_DIALOG_DATA) public data: any,
      public dateService : DateService) { }

  form = new FormGroup({
    clientName: new FormControl('', Validators.required),
    startDate: new FormControl('', Validators.required),
    endDate: new FormControl('', Validators.required),
    price: new FormControl(0, Validators.required),
    ownerEmail: new FormControl('', Validators.required),
    content: new FormControl('', Validators.required),
    answer: new FormControl('', Validators.required)
  })

  ngOnInit(): void {
    this.form.controls['clientName'].setValue(this.data.clientName)
    this.form.controls['startDate'].setValue(this.dateService.getDate(this.data.startDate).toLocaleString())
    this.form.controls['endDate'].setValue(this.dateService.getDate(this.data.endDate).toLocaleString())
    this.form.controls['price'].setValue(this.data.price)
    this.form.controls['ownerEmail'].setValue(this.data.ownerEmail)
    this.form.controls['content'].setValue(this.data.content)

  }

  close() {
    this.dialogRef.close();
  }

  send() {
    this.dialogRef.close(this.form.controls['answer'].value);
  }


}
