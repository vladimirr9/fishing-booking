import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.scss']
})
export class ReportDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ReportDialogComponent>) { }

  form = new FormGroup({
    comment: new FormControl('', Validators.required),
    sanction: new FormControl(false, Validators.required),
    appeared: new FormControl(true, Validators.required),
  })

  ngOnInit(): void {
  }

  submit() {
    this.dialogRef.close(this.form.value);
  }
  close() {
    this.dialogRef.close();
  }

}
