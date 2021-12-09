import { Component, OnInit } from '@angular/core';
import { AdventureService } from 'src/app/service/adventure.service';
import { ProviderRegistrationService } from 'src/app/service/provider-registration.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

  constructor(private providerRegistrationService: ProviderRegistrationService, private userService: UserService, private adventureService: AdventureService) { }

  adventures: any
  registrations: any
  users: any

  displayedColumnsServices: string[] = ['name', 'owner', 'deletion']
  displayedColumns: string[] = ['email', 'firstName', 'lastName', 'role', 'deletion'];
  ngOnInit(): void {
    this.providerRegistrationService.getAllRegistrations().subscribe((data: any) => {
      this.registrations = data
    })
    this.userService.getAll().subscribe((data : any)=> {
      this.users = data
    })
    this.adventureService.getAdventuresForInstructor(null).subscribe((data:any) => {
      this.adventures = data
    })
  }

  view() {
    //TODO: action to view registration request
  }

  deleteUser(user:any){
    this.userService.deleteUser(user.id).subscribe((data) => {
      let index = this.users.indexOf(user);
      if (index !== -1) {
        this.users.splice(index, 1);
      }
      this.users = [... this.users]
    })
  }
  deleteService(service: any) {
    this.adventureService.deleteAdventure(service.id).subscribe((data) => {
      let index = this.adventures.indexOf(service);
      if (index !== -1) {
        this.adventures.splice(index, 1);
      }
      this.adventures = [... this.adventures]
    })
  }

}
