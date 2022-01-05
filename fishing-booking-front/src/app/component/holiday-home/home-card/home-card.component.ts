import { Component, Input, OnInit, Output } from '@angular/core';
import { HolidayHomeService } from 'src/app/service/holiday-home.service';
import { AuthService } from 'src/app/service/auth.service';
import { StorageService } from 'src/app/service/storage.service';
import { EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-card',
  templateUrl: './home-card.component.html',
  styleUrls: ['./home-card.component.scss']
})
export class HomeCardComponent implements OnInit {

  @Input() home : any


  @Output() homeDeleted = new EventEmitter()
  constructor(private storageService: StorageService, private holidayHomeService: HolidayHomeService, private router: Router) { }

  ngOnInit(): void {
  }

  view() {
    this.router.navigateByUrl(`holiday-homes/${this.home.id}`)
  }

  isHomeOwner() : boolean {
    return this.storageService.getUsername() === this.home.homeOwner.email
  }

  editHome() {
    this.router.navigateByUrl(`holiday-homes/${this.home.id}/edit`)
  }

  deleteHome() {

    this.holidayHomeService.deleteHome(this.home.id).subscribe((data) => {
      this.homeDeleted.emit(this.home)
    })
  }
}
