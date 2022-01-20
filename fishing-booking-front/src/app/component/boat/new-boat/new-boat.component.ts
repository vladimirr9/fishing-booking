import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Map } from 'ol';
import View from 'ol/View';

import OSM from 'ol/source/OSM';
import * as olProj from 'ol/proj';
import TileLayer from 'ol/layer/Tile';
import { toLonLat } from 'ol/proj';
import { transform } from 'ol/proj';
import { BoatService } from 'src/app/service/boat.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { PictureDialogComponent } from '../../dialog/picture-dialog/picture-dialog.component';
import { PromotionDialogComponent } from '../../dialog/promotion-dialog/promotion-dialog.component';
import { BoatPromotion } from 'src/app/model/BoatPromotion';
import { AvailablePeriodService } from 'src/app/service/available-period.service';
import { StorageService } from 'src/app/service/storage.service';
import { AvailablePeriod } from 'src/app/model/AvailablePeriod';

@Component({
  selector: 'app-new-boat',
  templateUrl: './new-boat.component.html',
  styleUrls: ['./new-boat.component.scss']
})
export class NewBoatComponent implements OnInit {

  lon = 19.7245
  lat = 45.2889
  submitFailed = false
  editMode = false
  hasCabin = false
  map!: Map
  interior : any
  exterior : any
  promotions !: BoatPromotion[]

  fromTime: any
  toTime: any

  constructor(private fb: FormBuilder,
    private boatService: BoatService,
    private router: Router,
    private route: ActivatedRoute, private dialog: MatDialog, private availablePeriodService: AvailablePeriodService, private storageService: StorageService) { }


    addBoatForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      length: [0, Validators.required],
      engineNumber: [0, Validators.required],
      enginePower: [0, Validators.required],
      maxSpeed: [0, Validators.required],
      gps: [false, Validators.required],
      radar: [false, Validators.required],
      vhf: [false, Validators.required],
      fishfinder: [false, Validators.required],
      cabin: [false, Validators.required],
      description: ['', Validators.required],
      capacity: [0, Validators.required],
      fishingEquipment: [''],
      pricePerDay: [0, Validators.required],
      cancellationFeePercentage: [0, Validators.required],
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
        this.boatService.getBoat(id).subscribe((data) => {
          this.addBoatForm.controls['name'].setValue(data.name)
          this.addBoatForm.controls['type'].setValue(data.type)
          this.addBoatForm.controls['length'].setValue(data.length)
          this.addBoatForm.controls['engineNumber'].setValue(data.engineNumber)
          this.addBoatForm.controls['enginePower'].setValue(data.enginePower)
          this.addBoatForm.controls['maxSpeed'].setValue(data.maxSpeed)
          this.addBoatForm.controls['gps'].setValue(data.gps)
          this.addBoatForm.controls['radar'].setValue(data.radar)
          this.addBoatForm.controls['vhf'].setValue(data.vhf)
          this.addBoatForm.controls['fishfinder'].setValue(data.fishfinder)
          this.addBoatForm.controls['cabin'].setValue(data.cabin)
          this.addBoatForm.controls['description'].setValue(data.description)
          this.addBoatForm.controls['capacity'].setValue(data.capacity)
          this.addBoatForm.controls['fishingEquipment'].setValue(data.fishingEquipment)
          this.addBoatForm.controls['pricePerDay'].setValue(data.pricePerDay)
          this.addBoatForm.controls['cancellationFeePercentage'].setValue(data.cancellationFeePercentage)
          this.addBoatForm.controls['rulesOfConduct'].setValue(data.rulesOfConduct)
          this.addBoatForm.controls['additionalInfo'].setValue(data.additionalInfo)
          this.addBoatForm.controls['country'].setValue(data.address.country)
          this.addBoatForm.controls['city'].setValue(data.address.city)
          this.addBoatForm.controls['streetAndNumber'].setValue(data.address.streetAndNumber)
          this.addBoatForm.controls['latitude'].setValue(data.address.latitude)
          this.addBoatForm.controls['longitude'].setValue(data.address.longitude)
          
          this.lon = data.address.longitude
          this.lat = data.address.latitude
          this.hasCabin = data.cabin
          this.map.getView().setCenter(transform([this.lon, this.lat], 'EPSG:4326', 'EPSG:3857'));
          this.exterior = data.exterior
          this.interior = data.interior
        })

        this.boatService.getPromotions(id).subscribe((data) => {
          this.promotions = data;
        })
      }
    }

    addAvailablePeriod() {
      let availablePeriod = {
        fromTime: this.fromTime,
        toTime: this.toTime,
        email: this.storageService.getUsername()
      }
      this.availablePeriodService.postPeriodBoatOwner(availablePeriod, this.route.snapshot.params['id']).subscribe((data: AvailablePeriod) => {
         alert("Successfully added!")
      }, (error) => {
        alert("Error.")
      })
    }

    addPicture(isInterior: boolean) {

      const dialogConfig = new MatDialogConfig();
  
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
  
      const dialogRef = this.dialog.open(PictureDialogComponent, dialogConfig);
  
      dialogRef.afterClosed().subscribe(
        data => {
          if (data) {
            this.boatService.postPicture(this.route.snapshot.params['id'], data, isInterior).subscribe((result:any) => {
              this.interior = result.interior
              this.exterior = result.exterior
            })
          }
        }
      );
    }

    deletePicture(picture : any, isInterior: boolean) {
      this.boatService.deletePicture(this.route.snapshot.params['id'], picture.id, isInterior).subscribe((data) => {
        
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

    createBoat() {
      if (this.addBoatForm.invalid) {
        this.submitFailed = true
        return;
      }
      this.submitFailed = false
  
      let boat = {
        name: this.addBoatForm.controls['name'].value,
        type:this.addBoatForm.controls['type'].value,
        length:this.addBoatForm.controls['length'].value,
        engineNumber:this.addBoatForm.controls['engineNumber'].value,
        enginePower:this.addBoatForm.controls['enginePower'].value,
        maxSpeed :this.addBoatForm.controls['maxSpeed'].value,
        gps:this.addBoatForm.controls['gps'].value,
        radar :this.addBoatForm.controls['radar'].value,
        vhf :this.addBoatForm.controls['vhf'].value,
        fishfinder :this.addBoatForm.controls['fishfinder'].value,
        cabin  :this.addBoatForm.controls['cabin'].value,
        description :this.addBoatForm.controls['description'].value,
        capacity  :this.addBoatForm.controls['capacity'].value,
        fishingEquipment :this.addBoatForm.controls['fishingEquipment'].value,
        pricePerDay :this.addBoatForm.controls['pricePerDay'].value,
        cancellationFeePercentage :this.addBoatForm.controls['cancellationFeePercentage'].value,
        rulesOfConduct  :this.addBoatForm.controls['rulesOfConduct'].value,
        additionalInfo :this.addBoatForm.controls['additionalInfo'].value,
        country :this.addBoatForm.controls['country'].value,
        city  :this.addBoatForm.controls['city'].value,
        streetAndNumber :this.addBoatForm.controls['streetAndNumber'].value,
        latitude :this.addBoatForm.controls['latitude'].value,
        longitude :this.addBoatForm.controls['longitude'].value,
      }
  
      this.boatService.createNewBoat(boat).subscribe((data) => {
        this.router.navigateByUrl('boat-owner-home')
      })
    }

    getCoord(event: any) {
      let coordinate = this.map.getEventCoordinate(event);
      let xy = toLonLat(coordinate);
      this.addBoatForm.controls['longitude'].setValue(xy[0])
      this.addBoatForm.controls['latitude'].setValue(xy[1])
    }

    editBoat() {
      if (this.addBoatForm.invalid) {
        this.submitFailed = true
        return;
      }
      this.submitFailed = false
      let id = this.route.snapshot.params['id'];
      let boat = {
        name: this.addBoatForm.controls['name'].value,
        type:this.addBoatForm.controls['type'].value,
        length:this.addBoatForm.controls['length'].value,
        engineNumber:this.addBoatForm.controls['engineNumber'].value,
        enginePower:this.addBoatForm.controls['enginePower'].value,
        maxSpeed :this.addBoatForm.controls['maxSpeed'].value,
        gps:this.addBoatForm.controls['gps'].value,
        radar :this.addBoatForm.controls['radar'].value,
        vhf :this.addBoatForm.controls['vhf'].value,
        fishfinder :this.addBoatForm.controls['fishfinder'].value,
        cabin  :this.addBoatForm.controls['cabin'].value,
        description :this.addBoatForm.controls['description'].value,
        capacity  :this.addBoatForm.controls['capacity'].value,
        fishingEquipment :this.addBoatForm.controls['fishingEquipment'].value,
        pricePerDay :this.addBoatForm.controls['pricePerDay'].value,
        cancellationFeePercentage :this.addBoatForm.controls['cancellationFeePercentage'].value,
        rulesOfConduct  :this.addBoatForm.controls['rulesOfConduct'].value,
        additionalInfo :this.addBoatForm.controls['additionalInfo'].value,
        country :this.addBoatForm.controls['country'].value,
        city  :this.addBoatForm.controls['city'].value,
        streetAndNumber :this.addBoatForm.controls['streetAndNumber'].value,
        latitude :this.addBoatForm.controls['latitude'].value,
        longitude :this.addBoatForm.controls['longitude'].value,
      }
      this.boatService.updateBoat(id, boat).subscribe((data) => {
        this.router.navigateByUrl('boat-owner-home')
      })
    }

    Cancel() {
      this.router.navigateByUrl('boat-owner-home')
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
      return this.editMode ? "Edit Boat" : "New Boat"
    }
    getButtonText() {
      return this.editMode ? "Edit" : "Create"
    }
  
    addPromotion() {
      let id = this.route.snapshot.params['id'];
      const dialogConfig = new MatDialogConfig();
  
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
  
      const dialogRef = this.dialog.open(PromotionDialogComponent, dialogConfig);
  
      dialogRef.afterClosed().subscribe(
        data => {
          if (data) {
            this.boatService.postPromotion(id,data).subscribe((result:any) => {
              this.boatService.getPromotions(id).subscribe((data) => {
                this.promotions = data;
              })
            })
          }
        }
      );
    }
  
    deletePromotion(boatPromotion : BoatPromotion) {
      let index = this.promotions.indexOf(boatPromotion)
      if (index !== -1) {
        this.promotions.splice(index, 1);
      }
    }

}
