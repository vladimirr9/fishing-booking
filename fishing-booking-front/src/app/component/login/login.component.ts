import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CredentialsDTO } from 'src/app/dto/CredentialsDTO';
import { AuthService } from 'src/app/service/auth.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private storageService: StorageService, private router: Router) { }

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })
  loginFailed : boolean = false




  ngOnInit(): void {
  }

  onSubmit(): void {
    this.loginFailed = false
    if (this.loginForm.invalid) {
      return;
    }
    let credentials: CredentialsDTO = {email: this.loginForm.get('username')?.value, password: this.loginForm.get('password')?.value}
    this.authService.login(credentials).subscribe((data: any) => {
      this.storageService.storeTokenData(data.token)
      this.router.navigateByUrl('/')

    }, (err : Error) => {
      this.loginFailed = true
    })

  }
}
