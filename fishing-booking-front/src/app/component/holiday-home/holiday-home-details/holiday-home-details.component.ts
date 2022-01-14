import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Map } from 'ol';
import View from 'ol/View';

import OSM from 'ol/source/OSM';
import * as olProj from 'ol/proj';
import TileLayer from 'ol/layer/Tile';
import { toLonLat } from 'ol/proj';
import { transform } from 'ol/proj';
import { HolidayHomeService } from 'src/app/service/holiday-home.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { PictureDialogComponent } from '../../dialog/picture-dialog/picture-dialog.component';

@Component({
  selector: 'app-holiday-home-details',
  templateUrl: './holiday-home-details.component.html',
  styleUrls: ['./holiday-home-details.component.scss']
})
export class HolidayHomeDetailsComponent implements OnInit {

  lon = 19.7245
  lat = 45.2889
  map!: Map
  interior : any
  holidayhome : any
  exterior : any
   //client
   startingDate: Date= new Date();
   endingDate: Date = new Date();
  

  constructor(private fb: FormBuilder,
    private holidayHomeService: HolidayHomeService,
    private router: Router,
    private route: ActivatedRoute, private dialog: MatDialog) { }


    addHomeForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      roomsPerHome: [0, Validators.required],
      bedsPerRoom: [0, Validators.required],
      additionalInfo: [''],
      rulesOfConduct: [''],
      country: ['', Validators.required],
      city: ['', Validators.required],
      streetAndNumber: ['', Validators.required],
      latitude: [0, Validators.required],
      longitude: [0, Validators.required],
      pricePerDay: [0,Validators.required]
    })

    ngOnInit(): void {
      this.initMap(this.lon, this.lat);
       this.initializeForm();
       this.startingDate = this.route.snapshot.params['startDate'];
       this.endingDate = this.route.snapshot.params['endDate'];
    }

    initializeForm(): void{
      let id = this.route.snapshot.params['id'];
        this.holidayHomeService.getHome(id).subscribe((data) => {
          this.holidayhome = data;
          this.addHomeForm.controls['name'].setValue(data.name)
          this.addHomeForm.controls['description'].setValue(data.description)
          this.addHomeForm.controls['roomsPerHome'].setValue(data.roomsPerHome)
          this.addHomeForm.controls['bedsPerRoom'].setValue(data.bedsPerRoom)
          this.addHomeForm.controls['rulesOfConduct'].setValue(data.rulesOfConduct)
          this.addHomeForm.controls['additionalInfo'].setValue(data.additionalInfo)
          this.addHomeForm.controls['country'].setValue(data.address.country)
          this.addHomeForm.controls['city'].setValue(data.address.city)
          this.addHomeForm.controls['streetAndNumber'].setValue(data.address.streetAndNumber)
          this.addHomeForm.controls['latitude'].setValue(data.address.latitude)
          this.addHomeForm.controls['longitude'].setValue(data.address.longitude)
          this.addHomeForm.controls['pricePerDay'].setValue(data.pricePerDay)
          this.lon = data.address.longitude
          this.lat = data.address.latitude
          this.map.getView().setCenter(transform([this.lon, this.lat], 'EPSG:4326', 'EPSG:3857'));
          this.exterior = data.exterior
          this.interior = data.interior
        })
    }

    

  


    getCoord(event: any) {
      let coordinate = this.map.getEventCoordinate(event);
      let xy = toLonLat(coordinate);
      this.addHomeForm.controls['longitude'].setValue(xy[0])
      this.addHomeForm.controls['latitude'].setValue(xy[1])
    }

  

  
  
    initMap(lon: number, lat: number,) {
      this.map = new Map({
        target: 'map',
        layers: [
          new TileLayer({
            source: new OSM()
          })
        ],
        view: new View({
          center: olProj.fromLonLat([lon, lat]),
          zoom: 12
        })
      });
    }
    
  

}
