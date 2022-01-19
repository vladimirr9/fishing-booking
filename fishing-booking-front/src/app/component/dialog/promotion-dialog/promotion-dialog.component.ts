import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DateService } from 'src/app/service/date.service';

@Component({
  selector: 'app-promotion-dialog',
  templateUrl: './promotion-dialog.component.html',
  styleUrls: ['./promotion-dialog.component.scss']
})
export class PromotionDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<PromotionDialogComponent>, private dateService: DateService) { }

  form = new FormGroup({
    fromTime: new FormControl('', Validators.required),
    toTime: new FormControl('', Validators.required),
    price: new FormControl(0, Validators.required),
    validUntil: new FormControl('', Validators.required)
  })

  ngOnInit(): void {
  }

  submit() {
    this.dialogRef.close(this.form.value);
  }
  close() {
    this.dialogRef.close();
  }

  isFormValid() {
    if (!this.form.valid)
      return false
    let fromDate = this.dateService.getDate(this.form.controls['fromTime'].value)
    let toDate = this.dateService.getDate(this.form.controls['toTime'].value)
    let validDate = this.dateService.getDate(this.form.controls['validUntil'].value)
    return validDate < fromDate && fromDate < toDate
  }

}
