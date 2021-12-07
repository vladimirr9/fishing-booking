import { Component, Input, OnInit, Output } from '@angular/core';
import { AdventureService } from 'src/app/service/adventure.service';
import { AuthService } from 'src/app/service/auth.service';
import { StorageService } from 'src/app/service/storage.service';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-adventure-card',
  templateUrl: './adventure-card.component.html',
  styleUrls: ['./adventure-card.component.scss']
})
export class AdventureCardComponent implements OnInit {

  @Input() adventure : any


  @Output() adventureDeleted = new EventEmitter()
  constructor(private storageService: StorageService, private adventureService: AdventureService) { }

  ngOnInit(): void {
  }


  isAdventureOwner() : boolean {

    return this.storageService.getUsername() === this.adventure.fishingInstructor.email
  }

  editAdventure() {

  }

  deleteAdventure() {

    this.adventureService.deleteAdventure(this.adventure.id).subscribe((data) => {
      this.adventureDeleted.emit(this.adventure)
    })
  }
}
