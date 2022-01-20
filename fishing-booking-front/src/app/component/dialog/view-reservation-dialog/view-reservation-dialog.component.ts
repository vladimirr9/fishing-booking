import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-view-reservation-dialog',
  templateUrl: './view-reservation-dialog.component.html',
  styleUrls: ['./view-reservation-dialog.component.scss']
})
export class ViewReservationDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ViewReservationDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  id!: number

  form = new FormGroup({
    name: new FormControl('', Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    startDate: new FormControl('', Validators.required),
    endDate: new FormControl('', Validators.required),
    price: new FormControl(0, Validators.required)
  })

  ngOnInit(): void {
    console.log(this.data);

    this.form.controls['name'].setValue(this.data.name)
    this.form.controls['firstName'].setValue(this.data.firstName)
    this.form.controls['lastName'].setValue(this.data.lastName)
    this.form.controls['startDate'].setValue(this.data.startDate)
    this.form.controls['endDate'].setValue(this.data.endDate)
    this.form.controls['price'].setValue(this.data.price)
    this.id = this.data.id
  }

  approve() {
    this.dialogRef.close(this.id)
  }

  close() {
    this.dialogRef.close();
  }
}
