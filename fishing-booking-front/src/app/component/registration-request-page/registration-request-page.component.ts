import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { RegistrationDTO } from 'src/app/dto/RegistrationDTO';
import { AuthService } from 'src/app/service/auth.service';
import { ProviderRegistrationService } from 'src/app/service/provider-registration.service';
import { DenyRegistrationDialogComponent } from '../dialog/deny-registration-dialog/deny-registration-dialog.component';

@Component({
  selector: 'app-registration-request-page',
  templateUrl: './registration-request-page.component.html',
  styleUrls: ['./registration-request-page.component.scss']
})
export class RegistrationRequestPageComponent implements OnInit {

  id!: number

  constructor(private providerRegistrationService: ProviderRegistrationService,
     private router: Router,
     private route: ActivatedRoute,
     private dialog: MatDialog) { }

  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
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
    this.id = this.route.snapshot.params['id']
    this.providerRegistrationService.getRegistration(this.id).subscribe((data : any) => {
      this.form.controls['email'].setValue(data.email)
      this.form.controls['firstName'].setValue(data.firstName)
      this.form.controls['lastName'].setValue(data.lastName)
      this.form.controls['streetAndNumber'].setValue(data.streetAndNumber)
      this.form.controls['phoneNumber'].setValue(data.phoneNumber)
      this.form.controls['city'].setValue(data.city)
      this.form.controls['country'].setValue(data.country)
      this.form.controls['role'].setValue(data.role)
      this.form.controls['explanation'].setValue(data.explanation)
    })
  }

  approve() {
    this.providerRegistrationService.approve(this.id).subscribe((data) => {
      this.router.navigateByUrl('/admin-home')
    })
  }
  deny() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = {id: this.id}

    const dialogRef = this.dialog.open(DenyRegistrationDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      res => {
        if (res) {
          this.router.navigateByUrl('/admin-home')
        }
      }
    );

  }

}
