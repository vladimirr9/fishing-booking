import { Component, Input, OnInit, Output } from '@angular/core';
import { AdventureService } from 'src/app/service/adventure.service';
import { AuthService } from 'src/app/service/auth.service';
import { StorageService } from 'src/app/service/storage.service';
import { EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adventure-card',
  templateUrl: './adventure-card.component.html',
  styleUrls: ['./adventure-card.component.scss']
})
export class AdventureCardComponent implements OnInit {

  @Input() adventure : any


  @Output() adventureDeleted = new EventEmitter()
  constructor(private storageService: StorageService, private adventureService: AdventureService, private router: Router) { }

  ngOnInit(): void {
  }



  view() {
    this.router.navigateByUrl(`adventures/${this.adventure.id}`)
  }

  isAdventureOwner() : boolean {
    return this.storageService.getUsername() === this.adventure.fishingInstructor.email
  }

  editAdventure() {
    this.router.navigateByUrl(`adventures/${this.adventure.id}/edit`)
  }

  deleteAdventure() {

    this.adventureService.deleteAdventure(this.adventure.id).subscribe((data) => {
      this.adventureDeleted.emit(this.adventure)
    })
  }
}
