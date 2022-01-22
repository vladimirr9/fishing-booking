import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.scss']
})
export class FilterComponent implements OnInit {

  filterName: string = "";
  filterAdress: string = "";
  filterMark: number=0;
  subscribed: boolean = false;
  // filterStartDate: Date = new Date();
  // filterEndDate: Date = new Date();

  constructor(private route: ActivatedRoute,private storageService: StorageService) { }

  ngOnInit(): void {
  }

  isRouteReservation(): boolean{
    return this.route.snapshot.toString().split(',')[0].split('/')[1].slice(0,-1) === "reservations"
  }

  markChanged(): void{
    //alert(this.filterStartDate);  
    //alert(this.filterEndDate);
  }

  isClient():boolean{
      return this.storageService.getRole()=='ROLE_CLIENT';
  }
}
