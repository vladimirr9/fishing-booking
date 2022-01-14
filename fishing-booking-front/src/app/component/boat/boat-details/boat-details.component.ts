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
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-boat-details',
  templateUrl: './boat-details.component.html',
  styleUrls: ['./boat-details.component.scss']
})
export class BoatDetailsComponent implements OnInit {

  lon = 19.7245
  lat = 45.2889
  submitFailed = false
  editMode = false
  hasCabin = false
  map!: Map
  interior : any
  exterior : any
  boat: any

  //client
  startingDate: Date= new Date();
  endingDate: Date = new Date();

  constructor(private fb: FormBuilder,
    private boatService: BoatService,
    private router: Router,
    private route: ActivatedRoute, private dialog: MatDialog) { }


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
      this.initializeForm()
      this.startingDate = new Date(this.route.snapshot.params['startDate']);
      this.endingDate = new Date(this.route.snapshot.params['endDate']);
    }

    initializeForm(): void{
      let id = this.route.snapshot.params['id'];
        this.boatService.getBoat(id).subscribe((data) => {
          this.boat = data;
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
    }

    getCoord(event: any) {
      let coordinate = this.map.getEventCoordinate(event);
      let xy = toLonLat(coordinate);
      this.addBoatForm.controls['longitude'].setValue(xy[0])
      this.addBoatForm.controls['latitude'].setValue(xy[1])
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
    
    getButtonText() {
      return this.editMode ? "Edit" : "Create"
    }

    reserve(): void{}
  
}
