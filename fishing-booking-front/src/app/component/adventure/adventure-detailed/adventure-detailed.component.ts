import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Feature, Map, View } from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import * as olProj from 'ol/proj';
import { toLonLat } from 'ol/proj';
import { transform } from 'ol/proj';
import { AdventureService } from 'src/app/service/adventure.service';
import { AuthService } from 'src/app/service/auth.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { PictureDialogComponent } from '../../dialog/picture-dialog/picture-dialog.component';
import { AdditionalServiceDialogComponent } from '../../dialog/additional-service-dialog/additional-service-dialog.component';


@Component({
  selector: 'app-adventure-detailed',
  templateUrl: './adventure-detailed.component.html',
  styleUrls: ['./adventure-detailed.component.scss']
})
export class AdventureDetailedComponent implements OnInit {

  id!: number
  adventure: any
  map!: Map
  services: any
  pictures: any
  finishedLoading = false
  constructor(private route: ActivatedRoute, private adventureService: AdventureService, public authService: AuthService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.initMap(19.7245, 45.2889)
    this.id = this.route.snapshot.params['id']
    this.adventureService.getAdventure(this.id).subscribe((data) => {
      this.adventure = data
      this.services = data.additionalService
      this.pictures = data.pictures
      this.map.getView().setCenter(transform([data.address.longitude, data.address.latitude], 'EPSG:4326', 'EPSG:3857'));


      this.finishedLoading = true
    })
  }

  addPicture() {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(PictureDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.adventureService.postPicture(this.id,data).subscribe((result:any) => {
            this.pictures = result.pictures
          })
        }
      }
    );
  }

  addAdditionalService() {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(AdditionalServiceDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.adventureService.postAdditionalService(this.id,data).subscribe((result:any) => {
            this.services = result.additionalService
          })
        }
      }
    );
  }


  removeService(additionalService : any) {
    this.adventureService.deleteAdditionalService(this.id, additionalService.id).subscribe((data) => {
      let index = this.services.indexOf(additionalService);
      if (index !== -1) {
        this.services.splice(index, 1);
      }
    })
  }
  deletePicture(picture : any) {
    this.adventureService.deletePicture(this.id, picture.id).subscribe((data) => {
      let index = this.pictures.indexOf(picture);
      if (index !== -1) {
        this.pictures.splice(index, 1);
      }
    })
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
        zoom: 19
      })
    });
  }

}
