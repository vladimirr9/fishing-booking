import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { PictureDialogComponent } from '../picture-dialog/picture-dialog.component';

@Component({
  selector: 'app-additional-service-dialog',
  templateUrl: './additional-service-dialog.component.html',
  styleUrls: ['./additional-service-dialog.component.scss']
})
export class AdditionalServiceDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<PictureDialogComponent>) { }

  form = new FormGroup({
    name: new FormControl('', Validators.required),
    price: new FormControl(0, Validators.required)
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
