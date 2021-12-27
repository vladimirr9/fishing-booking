import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AccountDeletion } from 'src/app/model/AccountDeletion';
import { AccountDeletionService } from 'src/app/service/account-deletion.service';

@Component({
  selector: 'app-account-deletion-dialog',
  templateUrl: './account-deletion-dialog.component.html',
  styleUrls: ['./account-deletion-dialog.component.scss']
})
export class AccountDeletionDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<AccountDeletionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: AccountDeletion,
    private accountDeletionService: AccountDeletionService) { }

  form = new FormGroup({
    email: new FormControl('', Validators.required),
    reason: new FormControl('', Validators.required)
  })



  ngOnInit(): void {
    this.form.controls['email'].setValue(this.data.email)
  }
  approve() {
    this.accountDeletionService.approveDeletion(this.data.id, this.form.controls['reason'].value).subscribe((data) => {
      this.dialogRef.close(true);
    })
  }
  deny() {
    this.accountDeletionService.denyDeletion(this.data.id, this.form.controls['reason'].value).subscribe((data) => {
      this.dialogRef.close(true);
    })
  }
  close() {
    this.dialogRef.close();
  }

}
