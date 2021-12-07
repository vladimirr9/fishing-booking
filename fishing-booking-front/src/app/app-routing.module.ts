import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstructorHomeComponent } from './component/instructor-home/instructor-home.component';
import { LoginComponent } from './component/login/login.component';
import { NewAdventureComponent } from './component/adventure/new-adventure/new-adventure.component';
import { RegistrationComponent } from './component/registration/registration.component';

const routes: Routes = [ { path: 'login', component: LoginComponent },
                         { path: 'signup', component: RegistrationComponent },
                         { path: 'instructor-home', component: InstructorHomeComponent },
                         { path: 'instructor-home/new-adventure', component:  NewAdventureComponent},
                         { path: 'adventures/:id/edit', component:  NewAdventureComponent},
                        ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
