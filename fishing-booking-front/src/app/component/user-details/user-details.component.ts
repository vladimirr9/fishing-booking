import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {

  constructor(private userService: UserService) { }

  @Input() username!: string
  userDetails: any
  initialDetails: any
  editMode = false
  id!: number

  userForm = new FormGroup({
    email: new FormControl('',Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    streetAndNumber: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    country: new FormControl('', Validators.required)
  })

  ngOnInit(): void {
    alert('a')
    this.userService.getProfile(this.username).subscribe((data : any) => {
      this.userDetails = data
      this.initialDetails = JSON.parse(JSON.stringify(data)); //clones, read docs for info
      this.id = data.id
      this.userForm.controls['email'].setValue(data.email)
      this.userForm.controls['firstName'].setValue(data.firstName)
      this.userForm.controls['lastName'].setValue(data.lastName)
      this.userForm.controls['streetAndNumber'].setValue(data.streetAndNumber)
      this.userForm.controls['phoneNumber'].setValue(data.phoneNumber)
      this.userForm.controls['city'].setValue(data.city)
      this.userForm.controls['country'].setValue(data.country)
    })
  }

  edit() {
    this.userDetails = {
      email: this.userForm.get('email')?.value,
      firstName: this.userForm.get('firstName')?.value,
      lastName: this.userForm.get('lastName')?.value,
      streetAndNumber: this.userForm.get('streetAndNumber')?.value,
      phoneNumber: this.userForm.get('phoneNumber')?.value,
      city: this.userForm.get('city')?.value,
      country: this.userForm.get('country')?.value
    }
    this.userService.updateProfile(this.id, this.userDetails).subscribe((data) => {
      this.userDetails = data
      this.initialDetails = JSON.parse(JSON.stringify(data));
      this.editMode = false
    })
  }

  cancel() {
    this.editMode = false

    this.userForm.controls['email'].setValue(this.initialDetails.email)
    this.userForm.controls['firstName'].setValue(this.initialDetails.firstName)
    this.userForm.controls['lastName'].setValue(this.initialDetails.lastName)
    this.userForm.controls['streetAndNumber'].setValue(this.initialDetails.streetAndNumber)
    this.userForm.controls['phoneNumber'].setValue(this.initialDetails.phoneNumber)
    this.userForm.controls['city'].setValue(this.initialDetails.city)
    this.userForm.controls['country'].setValue(this.initialDetails.country)
  }

}
