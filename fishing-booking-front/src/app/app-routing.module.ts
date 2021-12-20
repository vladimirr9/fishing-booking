import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstructorHomeComponent } from './component/home/instructor-home/instructor-home.component';
import { LoginComponent } from './component/login/login.component';
import { NewAdventureComponent } from './component/adventure/new-adventure/new-adventure.component';
import { RegistrationComponent } from './component/registration/registration.component';
import { AdventureDetailedComponent } from './component/adventure/adventure-detailed/adventure-detailed.component';

import { BoatsComponent } from './component/client-homepage/boats/boats.component';
import { InstructorsComponent } from './component/client-homepage/instructors/instructors.component';
import { HolidayHousesComponent } from './component/client-homepage/holiday-houses/holiday-houses.component';

import { InstructorProfileComponent } from './component/profile/instructor-profile/instructor-profile.component';
import { AdminHomeComponent } from './component/home/admin-home/admin-home.component';
import { AdminProfileComponent } from './component/profile/admin-profile/admin-profile.component';
import { AdminRegistrationComponent } from './component/admin-registration/admin-registration.component';
import { ClientProfileComponent } from './component/profile/client-profile/client-profile.component';
import { ReservationsComponent } from './component/client-homepage/reservations/reservations.component';
import { AccountDeletionPageComponent } from './component/account-deletion/account-deletion-page/account-deletion-page.component';
import { RegistrationRequestPageComponent } from './component/registration-request-page/registration-request-page.component';


const routes: Routes = [ { path: 'login', component: LoginComponent },
                         { path: 'signup', component: RegistrationComponent },
                         { path: 'instructor-home', component: InstructorHomeComponent },
                         { path: 'admin-home', component: AdminHomeComponent },
                         { path: 'admin-home/new-admin', component: AdminRegistrationComponent },
                         { path: 'admin-home/registration-requests/:id', component: RegistrationRequestPageComponent },
                         { path: 'instructor-home/new-adventure', component:  NewAdventureComponent},
                         { path: 'instructor-profile', component: InstructorProfileComponent },
                         { path: 'client-profile', component: ClientProfileComponent },
                         { path: 'admin-profile', component: AdminProfileComponent },
                         { path: 'account-deletions', component: AccountDeletionPageComponent },
                         { path: 'adventures/:id/edit', component:  NewAdventureComponent},
                         { path: 'adventures/:id', component:  AdventureDetailedComponent},
                         { path: 'client-homepage/boats', component: BoatsComponent},
                         { path: 'client-homepage/instructors', component: InstructorsComponent},
                         { path: 'client-homepage/holiday-houses', component: HolidayHousesComponent},
                         { path: 'client-homepage/reservations', component: ReservationsComponent}
                        ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
