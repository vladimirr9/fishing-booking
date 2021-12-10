import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { FeeService } from 'src/app/service/fee.service';

@Component({
  selector: 'app-change-fee-dialog',
  templateUrl: './change-fee-dialog.component.html',
  styleUrls: ['./change-fee-dialog.component.scss']
})
export class ChangeFeeDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ChangeFeeDialogComponent>, private feeService: FeeService) { }


  form = new FormGroup({
    fee: new FormControl(0, Validators.required)
  })


  ngOnInit(): void {
    this.feeService.getFee().subscribe((data: any) => {
      this.form.controls['fee'].setValue(data.fee)
    })
  }

  save() {
    this.dialogRef.close(this.form.value);
  }
  close() {
    this.dialogRef.close();
  }
}
