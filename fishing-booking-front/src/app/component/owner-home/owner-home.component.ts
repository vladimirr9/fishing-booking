import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-owner-home',
  templateUrl: './owner-home.component.html',
  styleUrls: ['./owner-home.component.scss']
})
export class OwnerHomeComponent implements OnInit {
  homes = []

  searchTerm : string = ""

  loadingPath = "/assets/img/loading-circle.gif"
  magGlassPath = "/assets/img/search.svg"
  loading : boolean = false

  constructor() { }

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.loading = true
    /*let params = this.getParams()
    this.adventureService.getAdventuresForInstructor(params).subscribe( (data) => {
      this.adventures = data
    }).add(()=>{
      this.loading = false
    })*/
  }

  getParams() {
    /*let params = Object()
    params.instructorName = this.storageService.getUsername()
    params.adventureName = this.searchTerm
    return params*/
  }
}
