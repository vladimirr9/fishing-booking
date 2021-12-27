import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

const MaterialCompontents:any = [
  MatButtonModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatGridListModule,
  MatInputModule,
  MatRadioModule,
  MatDatepickerModule,
  MatNativeDateModule 
];

@NgModule({
  imports: [ MaterialCompontents],
  exports: [MaterialCompontents],
  providers:[MatDatepickerModule]

})
export class MaterialModule { }
