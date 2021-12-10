import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AdventureService } from 'src/app/service/adventure.service';
import { FeeService } from 'src/app/service/fee.service';
import { ProviderRegistrationService } from 'src/app/service/provider-registration.service';
import { StorageService } from 'src/app/service/storage.service';
import { UserService } from 'src/app/service/user.service';
import { ChangeFeeDialogComponent } from '../../dialog/change-fee-dialog/change-fee-dialog.component';
import { ChangePasswordComponent } from '../../dialog/change-password/change-password.component';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

  constructor(private providerRegistrationService: ProviderRegistrationService,
     private userService: UserService,
      private adventureService: AdventureService,
       private storageService : StorageService,
       private dialog: MatDialog, private feeService: FeeService) { }

  adventures: any
  registrations: any
  users: any

  displayedColumnsServices: string[] = ['name', 'owner', 'deletion']
  displayedColumns: string[] = ['email', 'firstName', 'lastName', 'role', 'deletion'];
  ngOnInit(): void {
    this.userService.getProfile(this.storageService.getUsername()).subscribe((data : any) => {
      if (data.firstLogin) {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true
        dialogConfig.autoFocus = true
        dialogConfig.data = {
          firstLogin: data.firstLogin
        };

      const dialogRef = this.dialog.open(ChangePasswordComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(
        res => {
          this.userService.changePassword(data.email, res).subscribe((result:any) => {
            void(0) //observables are lazy if nothing is done it won't be executed
          })
      }
      );
      }
    })
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

  changeFee() {

    const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true
        dialogConfig.autoFocus = true

      const dialogRef = this.dialog.open(ChangeFeeDialogComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(
        res => {
          this.feeService.updateFee(res).subscribe((result) => {
            void(0)
          })
      }
      );
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
