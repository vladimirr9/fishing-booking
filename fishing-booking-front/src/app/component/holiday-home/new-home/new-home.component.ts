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
  selector: 'app-new-home',
  templateUrl: './new-home.component.html',
  styleUrls: ['./new-home.component.scss']
})
export class NewHomeComponent implements OnInit {
  lon = 19.7245
  lat = 45.2889
  submitFailed = false
  editMode = false
  map!: Map
  interior : any
  exterior : any

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
    })

    ngOnInit(): void {
      this.initMap(this.lon, this.lat);
      if (this.router.url.endsWith('edit')) {
        this.editMode = true
        let id = this.route.snapshot.params['id'];
        this.holidayHomeService.getHome(id).subscribe((data) => {
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
          this.lon = data.address.longitude
          this.lat = data.address.latitude
          this.map.getView().setCenter(transform([this.lon, this.lat], 'EPSG:4326', 'EPSG:3857'));
          this.exterior = data.exterior
          this.interior = data.interior
        })
      }
    }

    addPicture(isInterior: boolean) {

      const dialogConfig = new MatDialogConfig();
  
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
  
      const dialogRef = this.dialog.open(PictureDialogComponent, dialogConfig);
  
      dialogRef.afterClosed().subscribe(
        data => {
          if (data) {
            this.holidayHomeService.postPicture(this.route.snapshot.params['id'], data, isInterior).subscribe((result:any) => {
              this.interior = result.interior
              this.exterior = result.exterior
            })
          }
        }
      );
    }

    deletePicture(picture : any, isInterior: boolean) {
      this.holidayHomeService.deletePicture(this.route.snapshot.params['id'], picture.id, isInterior).subscribe((data) => {
        
        if(isInterior) {
          let index = this.interior.indexOf(picture);
          if (index !== -1) {
            this.interior.splice(index, 1);
          }
        }
        else {
          let index = this.exterior.indexOf(picture);
          if (index !== -1) {
            this.exterior.splice(index, 1);
          }
        }


      })
    }

    createHome() {
      if (this.addHomeForm.invalid) {
        this.submitFailed = true
        return;
      }
      this.submitFailed = false
  
      let home = {
        name: this.addHomeForm.controls['name'].value,
        description: this.addHomeForm.controls['description'].value,
        roomsPerHome: this.addHomeForm.controls['roomsPerHome'].value,
        bedsPerRoom: this.addHomeForm.controls['bedsPerRoom'].value,
        additionalInfo: this.addHomeForm.controls['additionalInfo'].value,
        rulesOfConduct: this.addHomeForm.controls['rulesOfConduct'].value,
        streetAndNumber: this.addHomeForm.controls['streetAndNumber'].value,
        city: this.addHomeForm.controls['city'].value,
        country: this.addHomeForm.controls['country'].value,
        latitude: this.addHomeForm.controls['latitude'].value,
        longitude: this.addHomeForm.controls['longitude'].value
      }
  
      this.holidayHomeService.createNewHome(home).subscribe((data) => {
        this.router.navigateByUrl('owner-home')
      })
    }

    getCoord(event: any) {
      let coordinate = this.map.getEventCoordinate(event);
      let xy = toLonLat(coordinate);
      this.addHomeForm.controls['longitude'].setValue(xy[0])
      this.addHomeForm.controls['latitude'].setValue(xy[1])
    }

    editHome() {
      if (this.addHomeForm.invalid) {
        this.submitFailed = true
        return;
      }
      this.submitFailed = false
      let id = this.route.snapshot.params['id'];
      let home = {
        name: this.addHomeForm.controls['name'].value,
        description: this.addHomeForm.controls['description'].value,
        roomsPerHome: this.addHomeForm.controls['roomsPerHome'].value,
        bedsPerRoom: this.addHomeForm.controls['bedsPerRoom'].value,
        additionalInfo: this.addHomeForm.controls['additionalInfo'].value,
        rulesOfConduct: this.addHomeForm.controls['rulesOfConduct'].value,
        streetAndNumber: this.addHomeForm.controls['streetAndNumber'].value,
        city: this.addHomeForm.controls['city'].value,
        country: this.addHomeForm.controls['country'].value,
        latitude: this.addHomeForm.controls['latitude'].value,
        longitude: this.addHomeForm.controls['longitude'].value
      }
      this.holidayHomeService.updateHome(id, home).subscribe((data) => {
        this.router.navigateByUrl('owner-home')
      })
    }

    Cancel() {
      this.router.navigateByUrl('owner-home')
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
    getTitle() {
      return this.editMode ? "Edit Holiday Home" : "New Holiday Home"
    }
    getButtonText() {
      return this.editMode ? "Edit" : "Create"
    }
  

}
