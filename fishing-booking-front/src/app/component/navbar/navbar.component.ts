import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(public authService: AuthService, private router: Router, private storageService: StorageService) { }

  ngOnInit(): void {

  }


  logOut() {
    this.authService.logOut()
    this.router.navigateByUrl("/login")
  }

  goToHome() {
    let role = this.storageService.getRole()
    switch (role) {
      case "ROLE_FISHING_INSTRUCTOR":
        this.router.navigateByUrl("/instructor-home")
        break
        case "ROLE_HOME_OWNER":
          this.router.navigateByUrl("/owner-home")
          break
      default:
        this.router.navigateByUrl("/")
    }
  }
  goToProfile() {
    let role = this.storageService.getRole()
    switch (role) {
      case "ROLE_FISHING_INSTRUCTOR":
        this.router.navigateByUrl("/instructor-profile")
        break
      default:
        this.router.navigateByUrl("/")
    }
  }
}
