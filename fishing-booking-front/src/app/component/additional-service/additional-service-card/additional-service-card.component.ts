import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AdventureService } from 'src/app/service/adventure.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-additional-service-card',
  templateUrl: './additional-service-card.component.html',
  styleUrls: ['./additional-service-card.component.scss']
})
export class AdditionalServiceCardComponent implements OnInit {

  constructor(private storageService : StorageService, private adventureService: AdventureService) { }

  @Input() additionalService : any
  @Output() serviceDeleted = new EventEmitter()

  isProvider() : boolean {
    return this.storageService.getRole() === "ROLE_FISHING_INSTRUCTOR" || this.storageService.getRole() === "ROLE_BOAT_OWNER" || this.storageService.getRole() === "ROLE_HOME_OWNER" 
  }

  deleteService() {
    this.serviceDeleted.emit(this.additionalService)
  }

  ngOnInit(): void {
  }

}
