import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HolidayHomeService } from 'src/app/service/holiday-home.service';
import { AuthService } from 'src/app/service/auth.service';
import { StorageService } from 'src/app/service/storage.service';

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

  constructor(private holidayHomeService: HolidayHomeService,
    private storageService: StorageService) { }

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.loading = true
    let params = this.getParams()
    this.holidayHomeService.getHomesForOwner(params).subscribe( (data) => {
      this.homes = data
    }).add(()=>{
      this.loading = false
    })
  }

  getParams() {
    let params = Object()
    params.homeOwnerUsername = this.storageService.getUsername()
    params.holidayHomeName = this.searchTerm
    return params
  }
}
