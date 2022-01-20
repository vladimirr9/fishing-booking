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
import { OwnerHomeComponent } from './component/owner-home/owner-home.component';
import { NewHomeComponent } from './component/holiday-home/new-home/new-home.component';
import { HomeListComponent } from './component/holiday-home/home-list/home-list.component';
import { HomeCardComponent } from './component/holiday-home/home-card/home-card.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomepageComponent } from './component/client-homepage/homepage/homepage.component';
import { BoatsComponent } from './component/client-homepage/boats/boats.component';
import { ViewComponent } from './component/client-homepage/boats/view/view.component';
import { FilterComponent } from './component/client-homepage/boats/filter/filter.component';
import { InstructorsComponent } from './component/client-homepage/instructors/instructors.component';
import { AdventureViewComponent } from './component/client-homepage/instructors/adventure-view/adventure-view.component';
import { HolidayHousesComponent } from './component/client-homepage/holiday-houses/holiday-houses.component';
import { HolidayHousesViewComponent } from './component/client-homepage/holiday-houses/holiday-houses-view/holiday-houses-view.component';
import { SorterComponent } from './component/client-homepage/sorter/sorter.component';
import { AdminHomeComponent } from './component/home/admin-home/admin-home.component';
import { AdminProfileComponent } from './component/profile/admin-profile/admin-profile.component';
import { AdminRegistrationComponent } from './component/admin-registration/admin-registration.component';
import { ChangeFeeDialogComponent } from './component/dialog/change-fee-dialog/change-fee-dialog.component';
import { HomeOwnerProfileComponent } from './component/profile/home-owner-profile/home-owner-profile.component';
import { ClientProfileComponent } from './component/profile/client-profile/client-profile.component';
import { ReservationsComponent } from './component/client-homepage/reservations/reservations.component';
import { ReservationViewComponent } from './component/client-homepage/reservations/reservation-view/reservation-view.component';
import { ReservationSorterComponent } from './component/client-homepage/reservations/reservation-sorter/reservation-sorter.component';
import {MatSelectModule} from '@angular/material/select';
import { DeleteAccountComponent } from './component/dialog/delete-account/delete-account.component';
import { AccountDeletionCardComponent } from './component/account-deletion/account-deletion-card/account-deletion-card.component';
import { AccountDeletionPageComponent } from './component/account-deletion/account-deletion-page/account-deletion-page.component';
import { AccountDeletionDialogComponent } from './component/dialog/account-deletion-dialog/account-deletion-dialog.component';
import { RegistrationRequestPageComponent } from './component/registration-request-page/registration-request-page.component';
import { DenyRegistrationDialogComponent } from './component/dialog/deny-registration-dialog/deny-registration-dialog.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { InstructorCalendarPageComponent } from './component/home/instructor-home/instructor-calendar-page/instructor-calendar-page.component';
import { MaterialModule } from './material/material.module';
import { PromotionDialogComponent } from './component/dialog/promotion-dialog/promotion-dialog.component';
import { FishingPromotionCardComponent } from './component/promotion/fishing-promotion-card/fishing-promotion-card.component';
import { BoatOwnerHomeComponent } from './component/home/boat-owner-home/boat-owner-home.component';
import { BoatListComponent } from './component/boat/boat-list/boat-list.component';
import { BoatCardComponent } from './component/boat/boat-card/boat-card.component';
import { NewBoatComponent } from './component/boat/new-boat/new-boat.component';
import { BoatOwnerProfileComponent } from './component/profile/boat-owner-profile/boat-owner-profile.component';
import { ReserveAdventureComponent } from './component/client-homepage/reserve-adventure/reserve-adventure.component';
import { BoatDetailsComponent } from './component/boat/boat-details/boat-details.component';
import { ReserveBoatComponent } from './component/boat/reserve-boat/reserve-boat.component';
import { HolidayHomeDetailsComponent } from './component/holiday-home/holiday-home-details/holiday-home-details.component';
import { ReserveHolidayHomeComponent } from './component/holiday-home/reserve-holiday-home/reserve-holiday-home.component';
import { InstructorReservationsPageComponent } from './component/instructor-reservations-page/instructor-reservations-page.component';
import { ReportDialogComponent } from './component/dialog/report-dialog/report-dialog.component';
import { AdminReportsPageComponent } from './component/admin-reports-page/admin-reports-page.component';
import { AdminReportDialogComponent } from './component/dialog/admin-report-dialog/admin-report-dialog.component';
import { ComplaintDialogComponent } from './component/dialog/complaint-dialog/complaint-dialog.component';
import { ReviewDialogComponent } from './component/dialog/review-dialog/review-dialog.component';
import { ViewProfileDialogComponent } from './component/dialog/view-profile-dialog/view-profile-dialog.component';
import { BoatPromotionsComponent } from './component/boat/boat-promotions/boat-promotions.component';
import { HomePromotionsComponent } from './component/holiday-home/home-promotions/home-promotions.component';
import { ViewReservationDialogComponent } from './component/dialog/view-reservation-dialog/view-reservation-dialog.component';





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
    OwnerHomeComponent,
    NewHomeComponent,
    HomeListComponent,
    HomeCardComponent,
    HomepageComponent,
    BoatsComponent,
    ViewComponent,
    FilterComponent,
    InstructorsComponent,
    AdventureViewComponent,
    HolidayHousesComponent,
    HolidayHousesViewComponent,
    SorterComponent,
    AdminHomeComponent,
    AdminProfileComponent,
    AdminRegistrationComponent,
    ChangeFeeDialogComponent,
    HomeOwnerProfileComponent,
    ClientProfileComponent,
    ReservationsComponent,
    ReservationViewComponent,
    ReservationSorterComponent,
    DeleteAccountComponent,
    AccountDeletionCardComponent,
    AccountDeletionPageComponent,
    AccountDeletionDialogComponent,
    RegistrationRequestPageComponent,
    DenyRegistrationDialogComponent,
    InstructorCalendarPageComponent,
    PromotionDialogComponent,
    FishingPromotionCardComponent,
    BoatOwnerHomeComponent,
    BoatListComponent,
    BoatCardComponent,
    NewBoatComponent,
    BoatOwnerProfileComponent,
    ReserveAdventureComponent,
    InstructorReservationsPageComponent,
    BoatDetailsComponent,
    HolidayHomeDetailsComponent,
    ReserveBoatComponent,
    ReserveHolidayHomeComponent,
    ReportDialogComponent,
    AdminReportsPageComponent,
    AdminReportDialogComponent,
    ComplaintDialogComponent,
    ReviewDialogComponent,
    ViewProfileDialogComponent,
    BoatPromotionsComponent,
    HomePromotionsComponent,
    ViewReservationDialogComponent
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
    NgbModule,
    MatTableModule,
    MatSelectModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory }),
    MaterialModule
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
