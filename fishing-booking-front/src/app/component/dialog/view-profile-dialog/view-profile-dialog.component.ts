import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DateService } from 'src/app/service/date.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-view-profile-dialog',
  templateUrl: './view-profile-dialog.component.html',
  styleUrls: ['./view-profile-dialog.component.scss']
})
export class ViewProfileDialogComponent implements OnInit {


  constructor(private dialogRef: MatDialogRef<ViewProfileDialogComponent>, private userService: UserService, @Inject(MAT_DIALOG_DATA) public data: any) { }

  form = new FormGroup({
    email: new FormControl('', Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', Validators.required)
  })

  ngOnInit(): void {
    this.userService.getProfile(this.data.email).subscribe((data:any) => {
      this.form.controls['email'].setValue(data.email)
      this.form.controls['firstName'].setValue(data.firstName)
      this.form.controls['lastName'].setValue(data.lastName)
      this.form.controls['phoneNumber'].setValue(data.phoneNumber)
    })
  }

  close() {
    this.dialogRef.close();
  }


}
