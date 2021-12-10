import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstructorHomeComponent } from './component/instructor-home/instructor-home.component';
import { LoginComponent } from './component/login/login.component';
import { NewAdventureComponent } from './component/adventure/new-adventure/new-adventure.component';
import { RegistrationComponent } from './component/registration/registration.component';
import { AdventureDetailedComponent } from './component/adventure/adventure-detailed/adventure-detailed.component';
import { InstructorProfileComponent } from './component/instructor-profile/instructor-profile.component';
import { BoatsComponent } from './component/client-homepage/boats/boats.component';
import { InstructorsComponent } from './component/client-homepage/instructors/instructors.component';
import { HolidayHousesComponent } from './component/client-homepage/holiday-houses/holiday-houses.component';

const routes: Routes = [ { path: 'login', component: LoginComponent },
                         { path: 'signup', component: RegistrationComponent },
                         { path: 'instructor-home', component: InstructorHomeComponent },
                         { path: 'instructor-home/new-adventure', component:  NewAdventureComponent},
                         { path: 'instructor-profile', component: InstructorProfileComponent },
                         { path: 'adventures/:id/edit', component:  NewAdventureComponent},
                         { path: 'adventures/:id', component:  AdventureDetailedComponent},
                         { path: 'client-homepage/boats', component: BoatsComponent},
                         { path: 'client-homepage/instructors', component: InstructorsComponent},
                         { path: 'client-homepage/holiday-houses', component: HolidayHousesComponent}
                        ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
