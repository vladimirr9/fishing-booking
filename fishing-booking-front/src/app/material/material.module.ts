import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatInputModule} from '@angular/material/input';
import {MatRadioModule} from '@angular/material/radio';

const MaterialCompontents:any = [
  MatButtonModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatGridListModule,
  MatInputModule,
  MatRadioModule
];

@NgModule({
  imports: [ MaterialCompontents],
  exports: [MaterialCompontents]

})
export class MaterialModule { }
