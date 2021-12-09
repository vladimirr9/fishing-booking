import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationDTO } from 'src/app/dto/RegistrationDTO';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-admin-registration',
  templateUrl: './admin-registration.component.html',
  styleUrls: ['./admin-registration.component.scss']
})
export class AdminRegistrationComponent implements OnInit {


  constructor(private authService: AuthService, private router: Router) { }

  registrationForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    streetAndNumber: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    country: new FormControl('', Validators.required)
  })

  registrationFailed = false

  ngOnInit(): void {
  }

  onSubmit() : void {
    this.registrationFailed = false
    if (this.registrationForm.invalid) {
      return;
    }
    let adminRegistration : any = {
      email: this.registrationForm.get('email')?.value,
      password: this.registrationForm.get('password')?.value,
      firstName: this.registrationForm.get('firstName')?.value,
      lastName: this.registrationForm.get('lastName')?.value,
      streetAndNumber: this.registrationForm.get('streetAndNumber')?.value,
      phoneNumber: this.registrationForm.get('phoneNumber')?.value,
      city: this.registrationForm.get('city')?.value,
      country: this.registrationForm.get('country')?.value

    }
    this.authService.registerAdmin(adminRegistration).subscribe(() => {
      this.router.navigateByUrl('admin-home')
    }, (err: Error) => {
      this.registrationFailed = true;
    })
  }

}
