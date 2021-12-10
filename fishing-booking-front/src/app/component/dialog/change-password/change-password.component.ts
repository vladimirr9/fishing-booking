import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ChangePasswordComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }


  form = new FormGroup({
    currentPassword: new FormControl('', Validators.required),
    newPassword: new FormControl('', Validators.required)
  })

  canClose() {
    if (this.data) {
      return !this.data.firstLogin
    }
    else return true
  }

  ngOnInit(): void {
  }

  submit() {
    this.dialogRef.close(this.form.value);
  }
  close() {
    this.dialogRef.close();
  }

}
