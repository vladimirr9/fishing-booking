import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstructorHomeComponent } from './component/home/instructor-home/instructor-home.component';
import { LoginComponent } from './component/login/login.component';
import { NewAdventureComponent } from './component/adventure/new-adventure/new-adventure.component';
import { RegistrationComponent } from './component/registration/registration.component';
import { AdventureDetailedComponent } from './component/adventure/adventure-detailed/adventure-detailed.component';
import { OwnerHomeComponent } from './component/owner-home/owner-home.component';
import { NewHomeComponent } from './component/holiday-home/new-home/new-home.component';
import { BoatsComponent } from './component/client-homepage/boats/boats.component';
import { InstructorsComponent } from './component/client-homepage/instructors/instructors.component';
import { HolidayHousesComponent } from './component/client-homepage/holiday-houses/holiday-houses.component';
import { InstructorProfileComponent } from './component/profile/instructor-profile/instructor-profile.component';
import { AdminHomeComponent } from './component/home/admin-home/admin-home.component';
import { AdminProfileComponent } from './component/profile/admin-profile/admin-profile.component';
import { AdminRegistrationComponent } from './component/admin-registration/admin-registration.component';
import { HomeOwnerProfileComponent } from './component/profile/home-owner-profile/home-owner-profile.component';
import { ClientProfileComponent } from './component/profile/client-profile/client-profile.component';
import { ReservationsComponent } from './component/client-homepage/reservations/reservations.component';
import { AccountDeletionPageComponent } from './component/account-deletion/account-deletion-page/account-deletion-page.component';
import { RegistrationRequestPageComponent } from './component/registration-request-page/registration-request-page.component';
import { InstructorCalendarPageComponent } from './component/home/instructor-home/instructor-calendar-page/instructor-calendar-page.component';
import { BoatOwnerHomeComponent } from './component/home/boat-owner-home/boat-owner-home.component';
import { NewBoatComponent } from './component/boat/new-boat/new-boat.component';
import { BoatOwnerProfileComponent } from './component/profile/boat-owner-profile/boat-owner-profile.component';
import { BoatDetailsComponent } from './component/boat/boat-details/boat-details.component';
import { HolidayHomeDetailsComponent } from './component/holiday-home/holiday-home-details/holiday-home-details.component';


const routes: Routes = [ { path: 'login', component: LoginComponent },
                         { path: 'signup', component: RegistrationComponent },
                         { path: 'instructor-home', component: InstructorHomeComponent },
                         { path: 'admin-home', component: AdminHomeComponent },
                         { path: 'admin-home/new-admin', component: AdminRegistrationComponent },
                         { path: 'admin-home/registration-requests/:id', component: RegistrationRequestPageComponent },
                         { path: 'instructor-home/new-adventure', component:  NewAdventureComponent},
                         { path: 'instructor-home/calendar', component:  InstructorCalendarPageComponent},
                         { path: 'instructor-profile', component: InstructorProfileComponent },
                         { path: 'client-profile', component: ClientProfileComponent },
                         { path: 'admin-profile', component: AdminProfileComponent },
                         { path: 'account-deletions', component: AccountDeletionPageComponent },
                         { path: 'adventures/:id/edit', component:  NewAdventureComponent},
                         { path: 'adventures/:id', component:  AdventureDetailedComponent},
                         { path: 'adventures/:id/:date/:date2', component:  AdventureDetailedComponent},
                         { path: 'adventures/:id/:date', component:  AdventureDetailedComponent},
                         { path: 'owner-home', component:  OwnerHomeComponent},
                         { path: 'owner-home/new-home', component:  NewHomeComponent},
                         { path: 'holiday-homes/:id/edit', component:  NewHomeComponent},
                         { path: 'home-owner-profile', component: HomeOwnerProfileComponent },
                         { path: 'client-homepage/boats', component: BoatsComponent},
                         { path: 'client-homepage/instructors', component: InstructorsComponent},
                         { path: 'client-homepage/holiday-houses', component: HolidayHousesComponent},
                         { path: 'client-homepage/reservations', component: ReservationsComponent},
                         { path: 'boat-owner-home', component:  BoatOwnerHomeComponent},
                         { path: 'boat-owner-home/new-boat', component:  NewBoatComponent},
                         { path: 'boats/:id/edit', component:  NewBoatComponent},
                         { path: 'boat-owner-profile', component: BoatOwnerProfileComponent },
                         { path: 'boats/:id', component:  BoatDetailsComponent},
                         { path: 'boats/:id/:startDate/:endDate', component:  BoatDetailsComponent},
                         { path: 'holiday-home/:id',component: HolidayHomeDetailsComponent},
                         { path: 'holiday-home/:id/:startDate/:endDate',component: HolidayHomeDetailsComponent}
                        ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
