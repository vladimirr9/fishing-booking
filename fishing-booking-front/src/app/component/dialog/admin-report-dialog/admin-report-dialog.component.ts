import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-admin-report-dialog',
  templateUrl: './admin-report-dialog.component.html',
  styleUrls: ['./admin-report-dialog.component.scss']
})
export class AdminReportDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<AdminReportDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  form = new FormGroup({
    comment: new FormControl('', Validators.required),
    reason: new FormControl('', Validators.required),
    status: new FormControl('approve', Validators.required)
  })

  ngOnInit(): void {

    this.form.controls['comment'].setValue(this.data.comment)
  }

  close() {
    this.dialogRef.close();
  }
  send() {

    this.dialogRef.close(this.form.value);
  }
}
