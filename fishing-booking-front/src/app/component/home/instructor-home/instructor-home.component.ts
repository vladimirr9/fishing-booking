import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  searchTerm : string = ""

  loadingPath = "/assets/img/loading-circle.gif"
  magGlassPath = "/assets/img/search.svg"
  loading : boolean = false

  constructor(private adventureService: AdventureService,
              private storageService: StorageService) { }

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.loading = true
    let params = this.getParams()
    this.adventureService.getAdventuresForInstructor(params).subscribe( (data) => {
      this.adventures = data
    }).add(()=>{
      this.loading = false
    })
  }

  getParams() {
    let params = Object()
    params.instructorName = this.storageService.getUsername()
    params.adventureName = this.searchTerm
    return params
  }

}
