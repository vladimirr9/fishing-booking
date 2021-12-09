import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstructorHomeComponent } from './component/home/instructor-home/instructor-home.component';
import { LoginComponent } from './component/login/login.component';
import { NewAdventureComponent } from './component/adventure/new-adventure/new-adventure.component';
import { RegistrationComponent } from './component/registration/registration.component';
import { AdventureDetailedComponent } from './component/adventure/adventure-detailed/adventure-detailed.component';
import { InstructorProfileComponent } from './component/profile/instructor-profile/instructor-profile.component';
import { AdminHomeComponent } from './component/home/admin-home/admin-home.component';
import { AdminProfileComponent } from './component/profile/admin-profile/admin-profile.component';
import { AdminRegistrationComponent } from './component/admin-registration/admin-registration.component';

const routes: Routes = [ { path: 'login', component: LoginComponent },
                         { path: 'signup', component: RegistrationComponent },
                         { path: 'instructor-home', component: InstructorHomeComponent },
                         { path: 'admin-home', component: AdminHomeComponent },
                         { path: 'admin-home/new-admin', component: AdminRegistrationComponent },
                         { path: 'instructor-home/new-adventure', component:  NewAdventureComponent},
                         { path: 'instructor-profile', component: InstructorProfileComponent },
                         { path: 'admin-profile', component: AdminProfileComponent },
                         { path: 'adventures/:id/edit', component:  NewAdventureComponent},
                         { path: 'adventures/:id', component:  AdventureDetailedComponent},
                        ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
