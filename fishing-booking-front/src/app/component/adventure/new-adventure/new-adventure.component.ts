import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Map } from 'ol';
import View from 'ol/View';

import OSM from 'ol/source/OSM';
import * as olProj from 'ol/proj';
import TileLayer from 'ol/layer/Tile';
import { toLonLat } from 'ol/proj';
import { transform } from 'ol/proj';
import { AdventureService } from 'src/app/service/adventure.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-new-adventure',
  templateUrl: './new-adventure.component.html',
  styleUrls: ['./new-adventure.component.scss']
})
export class NewAdventureComponent implements OnInit {

  lon = 19.7245
  lat = 45.2889
  submitFailed = false
  editMode = false
  map!: Map
  constructor(private fb: FormBuilder,
    private adventureService: AdventureService,
    private router: Router,
    private route: ActivatedRoute) { }

  addAdventureForm = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.required],
    biography: [''],
    maxPeople: [0, Validators.required],
    rulesOfConduct: [''],
    availableEquipment: [''],
    cancellationFee: [0, [Validators.min(0), Validators.max(100), Validators.required]],
    hourlyPrice: [0, Validators.required],
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
      this.adventureService.getAdventure(id).subscribe((data) => {
        this.addAdventureForm.controls['name'].setValue(data.name)
        this.addAdventureForm.controls['description'].setValue(data.description)
        this.addAdventureForm.controls['biography'].setValue(data.biography)
        this.addAdventureForm.controls['maxPeople'].setValue(data.maxPeople)
        this.addAdventureForm.controls['rulesOfConduct'].setValue(data.rulesOfConduct)
        this.addAdventureForm.controls['availableEquipment'].setValue(data.availableEquipment)
        this.addAdventureForm.controls['cancellationFee'].setValue(data.cancellationFee)
        this.addAdventureForm.controls['hourlyPrice'].setValue(data.hourlyPrice)
        this.addAdventureForm.controls['country'].setValue(data.address.country)
        this.addAdventureForm.controls['city'].setValue(data.address.city)
        this.addAdventureForm.controls['streetAndNumber'].setValue(data.address.streetAndNumber)
        this.addAdventureForm.controls['latitude'].setValue(data.address.latitude)
        this.addAdventureForm.controls['longitude'].setValue(data.address.longitude)
        this.lon = data.address.longitude
        this.lat = data.address.latitude
        this.map.getView().setCenter(transform([this.lon, this.lat], 'EPSG:4326', 'EPSG:3857'));
      })
    }


  }




  getCoord(event: any) {
    let coordinate = this.map.getEventCoordinate(event);
    let xy = toLonLat(coordinate);
    this.addAdventureForm.controls['longitude'].setValue(xy[0])
    this.addAdventureForm.controls['latitude'].setValue(xy[1])
  }



  createAdventure() {
    if (this.addAdventureForm.invalid) {
      this.submitFailed = true
      return;
    }
    this.submitFailed = false

    let adventure = {
      name: this.addAdventureForm.controls['name'].value,
      description: this.addAdventureForm.controls['description'].value,
      biography: this.addAdventureForm.controls['biography'].value,
      maxPeople: this.addAdventureForm.controls['maxPeople'].value,
      rulesOfConduct: this.addAdventureForm.controls['rulesOfConduct'].value,
      availableEquipment: this.addAdventureForm.controls['availableEquipment'].value,
      cancellationFee: this.addAdventureForm.controls['cancellationFee'].value,
      hourlyPrice: this.addAdventureForm.controls['hourlyPrice'].value,
      streetAndNumber: this.addAdventureForm.controls['streetAndNumber'].value,
      city: this.addAdventureForm.controls['city'].value,
      country: this.addAdventureForm.controls['country'].value,
      latitude: this.addAdventureForm.controls['latitude'].value,
      longitude: this.addAdventureForm.controls['longitude'].value
    }

    this.adventureService.createNewAdventure(adventure).subscribe((data) => {
      this.router.navigateByUrl('instructor-home')
    })
  }

  editAdventure() {
    if (this.addAdventureForm.invalid) {
      this.submitFailed = true
      return;
    }
    this.submitFailed = false
    let id = this.route.snapshot.params['id'];
    let adventure = {
      name: this.addAdventureForm.controls['name'].value,
      description: this.addAdventureForm.controls['description'].value,
      biography: this.addAdventureForm.controls['biography'].value,
      maxPeople: this.addAdventureForm.controls['maxPeople'].value,
      rulesOfConduct: this.addAdventureForm.controls['rulesOfConduct'].value,
      availableEquipment: this.addAdventureForm.controls['availableEquipment'].value,
      cancellationFee: this.addAdventureForm.controls['cancellationFee'].value,
      hourlyPrice: this.addAdventureForm.controls['hourlyPrice'].value,
      streetAndNumber: this.addAdventureForm.controls['streetAndNumber'].value,
      city: this.addAdventureForm.controls['city'].value,
      country: this.addAdventureForm.controls['country'].value,
      latitude: this.addAdventureForm.controls['latitude'].value,
      longitude: this.addAdventureForm.controls['longitude'].value
    }
    this.adventureService.updateAdventure(id, adventure).subscribe((data) => {
      this.router.navigateByUrl('instructor-home')
    })
  }

  Cancel() {
    this.router.navigateByUrl('instructor-home')
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
    return this.editMode ? "Edit Adventure" : "New Adventure"
  }
  getButtonText() {
    return this.editMode ? "Edit" : "Create"
  }

}
