import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Map } from 'ol';
import View from 'ol/View';
import VectorLayer from 'ol/layer/Vector';
import Style from 'ol/style/Style';
import Icon from 'ol/style/Icon';
import OSM from 'ol/source/OSM';
import * as olProj from 'ol/proj';
import TileLayer from 'ol/layer/Tile';
import {toLonLat} from 'ol/proj';
import { AdventureService } from 'src/app/service/adventure.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-adventure',
  templateUrl: './new-adventure.component.html',
  styleUrls: ['./new-adventure.component.scss']
})
export class NewAdventureComponent implements OnInit {

  submitFailed = false

  map: any
  constructor(private fb: FormBuilder,
              private adventureService: AdventureService,
              private router: Router) { }

  addAdventureForm = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.required],
    biography: [''],
    maxNumberOfPeople: [0, Validators.required],
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
    this.map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new OSM()
        })
      ],
      view: new View({
        center: olProj.fromLonLat([19.8245,45.2589]),
        zoom: 12
      })
    });
  }


  getCoord(event: any){
    let coordinate = this.map.getEventCoordinate(event);
    let xy = toLonLat(coordinate);
    this.addAdventureForm.controls['longitude'].setValue(xy[0])
    this.addAdventureForm.controls['latitude'].setValue(xy[1])
 }


  createBook() {
    if (this.addAdventureForm.invalid) {
      this.submitFailed = true
      return;
    }
    this.submitFailed = false

    let adventure = {
    name: this.addAdventureForm.controls['name'].value,
    description: this.addAdventureForm.controls['description'].value,
    biography: this.addAdventureForm.controls['biography'].value,
    maxPeople: this.addAdventureForm.controls['maxNumberOfPeople'].value,
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

}
