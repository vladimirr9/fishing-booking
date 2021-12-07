import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { LoginComponent } from './component/login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { RegistrationComponent } from './component/registration/registration.component';
import { InstructorHomeComponent } from './component/instructor-home/instructor-home.component';
import { AdventureListComponent } from './component/adventure/adventure-list/adventure-list.component';
import { AdventureCardComponent } from './component/adventure/adventure-card/adventure-card.component';
import { NewAdventureComponent } from './component/adventure/new-adventure/new-adventure.component';
import { StorageService } from './service/storage.service';
import { AuthInterceptorService } from './service/auth-interceptor.service';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegistrationComponent,
    InstructorHomeComponent,
    AdventureListComponent,
    AdventureCardComponent,
    NewAdventureComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [StorageService ,
    {
     provide: HTTP_INTERCEPTORS,
     useClass: AuthInterceptorService,
     multi: true
    },],
  bootstrap: [AppComponent]
})
export class AppModule { }
