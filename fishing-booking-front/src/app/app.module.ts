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
import {MatDialogModule} from '@angular/material/dialog';
import {MatTableModule} from '@angular/material/table';
import { RegistrationComponent } from './component/registration/registration.component';
import { InstructorHomeComponent } from './component/home/instructor-home/instructor-home.component';
import { AdventureListComponent } from './component/adventure/adventure-list/adventure-list.component';
import { AdventureCardComponent } from './component/adventure/adventure-card/adventure-card.component';
import { NewAdventureComponent } from './component/adventure/new-adventure/new-adventure.component';
import { StorageService } from './service/storage.service';
import { AuthInterceptorService } from './service/auth-interceptor.service';
import { AdventureDetailedComponent } from './component/adventure/adventure-detailed/adventure-detailed.component';
import { AdditionalServiceCardComponent } from './component/additional-service/additional-service-card/additional-service-card.component';
import { PictureCardComponent } from './component/picture/picture-card/picture-card.component';
import { PictureDialogComponent } from './component/dialog/picture-dialog/picture-dialog.component';
import { AdditionalServiceDialogComponent } from './component/dialog/additional-service-dialog/additional-service-dialog.component';
import { InstructorProfileComponent } from './component/profile/instructor-profile/instructor-profile.component';
import { UserDetailsComponent } from './component/user-details/user-details.component';
import { ChangePasswordComponent } from './component/dialog/change-password/change-password.component';
import { AdminHomeComponent } from './component/home/admin-home/admin-home.component';
import { AdminProfileComponent } from './component/profile/admin-profile/admin-profile.component';
import { AdminRegistrationComponent } from './component/admin-registration/admin-registration.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegistrationComponent,
    InstructorHomeComponent,
    AdventureListComponent,
    AdventureCardComponent,
    NewAdventureComponent,
    AdventureDetailedComponent,
    AdditionalServiceCardComponent,
    PictureCardComponent,
    PictureDialogComponent,
    AdditionalServiceDialogComponent,
    InstructorProfileComponent,
    UserDetailsComponent,
    ChangePasswordComponent,
    AdminHomeComponent,
    AdminProfileComponent,
    AdminRegistrationComponent
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
    MatInputModule,
    MatDialogModule,
    MatTableModule
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
