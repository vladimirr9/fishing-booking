import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstructorHomeComponent } from './component/instructor-home/instructor-home.component';
import { LoginComponent } from './component/login/login.component';
import { NewAdventureComponent } from './component/adventure/new-adventure/new-adventure.component';
import { RegistrationComponent } from './component/registration/registration.component';
import { AdventureDetailedComponent } from './component/adventure/adventure-detailed/adventure-detailed.component';
import { InstructorProfileComponent } from './component/instructor-profile/instructor-profile.component';
import { OwnerHomeComponent } from './component/owner-home/owner-home.component';
import { NewHomeComponent } from './component/holiday-home/new-home/new-home.component';

const routes: Routes = [ { path: 'login', component: LoginComponent },
                         { path: 'signup', component: RegistrationComponent },
                         { path: 'instructor-home', component: InstructorHomeComponent },
                         { path: 'instructor-home/new-adventure', component:  NewAdventureComponent},
                         { path: 'instructor-profile', component: InstructorProfileComponent },
                         { path: 'adventures/:id/edit', component:  NewAdventureComponent},
                         { path: 'adventures/:id', component:  AdventureDetailedComponent},
                         { path: 'owner-home', component:  OwnerHomeComponent},
                         { path: 'owner-home/new-home', component:  NewHomeComponent},
                        ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
