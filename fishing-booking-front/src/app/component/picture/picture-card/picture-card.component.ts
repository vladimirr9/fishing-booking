import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-picture-card',
  templateUrl: './picture-card.component.html',
  styleUrls: ['./picture-card.component.scss']
})
export class PictureCardComponent implements OnInit {

  constructor(private storageService: StorageService) { }

  @Input() picture!: any
  @Output() pictureDeleted = new EventEmitter()

  isFishingInstructor() : boolean {
    return this.storageService.getRole() === "ROLE_FISHING_INSTRUCTOR"
  }

  deletePicture() {
    this.pictureDeleted.emit(this.picture)
  }
  ngOnInit(): void {
  }

}
