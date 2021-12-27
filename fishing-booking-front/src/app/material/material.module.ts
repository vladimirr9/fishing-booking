import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

const MaterialCompontents:any = [
  MatButtonModule,
  MatIconModule,
  MatProgressSpinnerModule
];

@NgModule({
  imports: [ MaterialCompontents],
  exports: [MaterialCompontents]

})
export class MaterialModule { }
