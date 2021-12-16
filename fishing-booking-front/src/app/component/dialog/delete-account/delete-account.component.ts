import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-delete-account',
  templateUrl: './delete-account.component.html',
  styleUrls: ['./delete-account.component.scss']
})
export class DeleteAccountComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<DeleteAccountComponent>) { }

  form = new FormGroup({
    reason: new FormControl('', Validators.required)
  })

  ngOnInit(): void {
  }


  submit() {
    if (this.form.invalid) {
      return
    }
    this.dialogRef.close(this.form.value);
  }
  close() {
    this.dialogRef.close();
  }

}
