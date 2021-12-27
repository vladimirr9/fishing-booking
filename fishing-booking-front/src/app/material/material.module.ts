import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatGridListModule} from '@angular/material/grid-list';

const MaterialCompontents:any = [
  MatButtonModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatGridListModule
];

@NgModule({
  imports: [ MaterialCompontents],
  exports: [MaterialCompontents]

})
export class MaterialModule { }
