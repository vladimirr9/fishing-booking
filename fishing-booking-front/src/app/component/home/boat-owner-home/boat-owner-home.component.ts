import { Component, OnInit } from '@angular/core';
import { BoatService } from 'src/app/service/boat.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-boat-owner-home',
  templateUrl: './boat-owner-home.component.html',
  styleUrls: ['./boat-owner-home.component.scss']
})
export class BoatOwnerHomeComponent implements OnInit {

  boats = []

  searchTerm : string = ""

  loadingPath = "/assets/img/loading-circle.gif"
  magGlassPath = "/assets/img/search.svg"
  loading : boolean = false

  constructor(private boatService: BoatService,
              private storageService: StorageService) { }

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.loading = true
    let params = this.getParams()
    this.boatService.getBoatsForOwner(params).subscribe( (data) => {
      this.boats = data
    }).add(()=>{
      this.loading = false
    })
  }

  getParams() {
    let params = Object()
    params.ownerName = this.storageService.getUsername()
    params.boatName = this.searchTerm
    return params
  }

}
