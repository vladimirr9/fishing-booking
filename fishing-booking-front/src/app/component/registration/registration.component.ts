import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationDTO } from 'src/app/dto/RegistrationDTO';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  registrationForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
    password2: new FormControl('', Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    streetAndNumber: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    country: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required),
    explanation: new FormControl(''),
  })


  registrationFailed = false

  ngOnInit(): void {
  }

  onSubmit() : void {
    this.registrationFailed = false
    if (this.registrationForm.invalid || this.registrationForm.get('password2')?.value !== this.registrationForm.get('password')?.value) {
      return;
    }
    let registrationDTO : RegistrationDTO = {
      email: this.registrationForm.get('email')?.value,
      password: this.registrationForm.get('password')?.value,
      firstName: this.registrationForm.get('firstName')?.value,
      lastName: this.registrationForm.get('lastName')?.value,
      streetAndNumber: this.registrationForm.get('streetAndNumber')?.value,
      phoneNumber: this.registrationForm.get('phoneNumber')?.value,
      city: this.registrationForm.get('city')?.value,
      country: this.registrationForm.get('country')?.value,
      role: this.registrationForm.get('role')?.value,
      explanation: this.registrationForm.get('explanation')?.value ?? ""
    }
    this.authService.registerProvider(registrationDTO).subscribe(() => {
      this.router.navigateByUrl('/login')
    }, (err: Error) => {
      this.registrationFailed = true;
    })
  }

  isProvider() : boolean {
    let role = this.registrationForm.get('role')?.value
    return role === "ROLE_HOME_OWNER" || role === "ROLE_BOAT_OWNER" || role === "ROLE_FISHING_INSTRUCTOR"
  }

}
