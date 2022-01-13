import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatExpansionModule} from '@angular/material/expansion';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';

const MaterialCompontents:any = [
  MatButtonModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatGridListModule,
  MatInputModule,
  MatRadioModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatExpansionModule,
  NgxMaterialTimepickerModule
];

@NgModule({
  imports: [ MaterialCompontents],
  exports: [MaterialCompontents],
  providers:[MatDatepickerModule]

})
export class MaterialModule { }
