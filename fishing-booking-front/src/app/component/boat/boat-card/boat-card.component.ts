import { Component, Input, OnInit, Output } from '@angular/core';
import { BoatService } from 'src/app/service/boat.service';
import { StorageService } from 'src/app/service/storage.service';
import { EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-boat-card',
  templateUrl: './boat-card.component.html',
  styleUrls: ['./boat-card.component.scss']
})
export class BoatCardComponent implements OnInit {

  @Input() boat : any


  @Output() boatDeleted = new EventEmitter()
  constructor(private storageService: StorageService, private boatService: BoatService, private router: Router) { }

  ngOnInit(): void {
  }

  view() {
    this.router.navigateByUrl(`boats/${this.boat.id}`)
  }

  isBoatOwner() : boolean {
    return this.storageService.getUsername() === this.boat.boatOwner.email
  }

  editBoat() {
    this.router.navigateByUrl(`boats/${this.boat.id}/edit`)
  }

  deleteBoat() {

    this.boatService.deleteBoat(this.boat.id).subscribe((data) => {
      this.boatDeleted.emit(this.boat)
    })
  }
}
