import { Component, OnInit } from '@angular/core';
import { AdventureService } from 'src/app/service/adventure.service';
import { AuthService } from 'src/app/service/auth.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-instructor-home',
  templateUrl: './instructor-home.component.html',
  styleUrls: ['./instructor-home.component.scss']
})
export class InstructorHomeComponent implements OnInit {

  adventures = []

  constructor(private adventureService: AdventureService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.adventureService.getAdventuresForInstructor(this.storageService.getUsername()).subscribe( (data) => {
      this.adventures = data
    })
  }

}
