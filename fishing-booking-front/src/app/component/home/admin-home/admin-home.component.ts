import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AdventureService } from 'src/app/service/adventure.service';
import { BoatService } from 'src/app/service/boat.service';
import { FeeService } from 'src/app/service/fee.service';
import { HolidayHomeService } from 'src/app/service/holiday-home.service';
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
    private storageService: StorageService,
    private dialog: MatDialog, private feeService: FeeService,
    private boatService: BoatService,
    private homeService: HolidayHomeService) { }

  adventures: any
  registrations: any
  boats: any
  homes: any
  users: any
  entities: any

  displayedColumnsServices: string[] = ['name', 'owner', 'deletion']
  displayedColumns: string[] = ['email', 'firstName', 'lastName', 'role', 'deletion'];
  ngOnInit(): void {

    this.userService.getProfile(this.storageService.getUsername()).subscribe((data: any) => {
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
            if (res) {
              this.userService.changePassword(data.email, res).subscribe((result: any) => {
                void (0) //observables are lazy if nothing is done it won't be executed
              })
            }
          }
        );
      }
    })
    let registrationParams = {
      exclude: "ROLE_CLIENT"
    }
    this.providerRegistrationService.getAllRegistrations(registrationParams).subscribe((data: any) => {
      this.registrations = data
    })
    this.userService.getAll().subscribe((data: any) => {
      this.users = data
    })
    this.fetchEntities();

  }

  private fetchEntities() {
    this.entities = []
    this.adventureService.getAdventuresForInstructor(null).subscribe((data: any) => {
      this.entities = [...this.entities, ...data.map((el: any) => {
        let reformated = {
          id: el.id,
          name: el.name,
          firstName: el.fishingInstructor.firstName,
          lastName: el.fishingInstructor.lastName,
          type: 'adventure'
        };
        return reformated;
      })];
    });

    this.boatService.get().subscribe((data: any) => {
      this.entities = [...this.entities, ...data.map((el: any) => {
        let reformated = {
          id: el.id,
          name: el.name,
          firstName: el.boatOwner.firstName,
          lastName: el.boatOwner.lastName,
          type: 'boat'
        };
        return reformated;
      })];
    });

    this.homeService.get().subscribe((data: any) => {
      this.entities = [...this.entities, ...data.map((el: any) => {
        let reformated = {
          id: el.id,
          name: el.name,
          firstName: el.homeOwner.firstName,
          lastName: el.homeOwner.lastName,
          type: 'home'
        };
        return reformated;
      })];
    });
  }

  changeFee() {

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true
    dialogConfig.autoFocus = true

    const dialogRef = this.dialog.open(ChangeFeeDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      res => {
        if (res) {
          this.feeService.updateFee(res).subscribe((result) => {
            void (0)
          })
        }
      }

    );
  }

  deleteUser(user: any) {
    this.userService.deleteUser(user.id).subscribe((data) => {
      let index = this.users.indexOf(user);
      if (index !== -1) {
        this.users.splice(index, 1);
      }
      this.users = [... this.users]
      this.fetchEntities()
    })

  }
  deleteService(service: any) {
    switch (service.type) {
      case 'adventure':
        this.adventureService.deleteAdventure(service.id).subscribe((data) => {
          let index = this.entities.indexOf(service);
          if (index !== -1) {
            this.entities.splice(index, 1);
          }
          this.entities = [... this.entities]
        })
        break
      case 'boat':
        this.boatService.deleteBoat(service.id).subscribe((data) => {
          let index = this.entities.indexOf(service);
          if (index !== -1) {
            this.entities.splice(index, 1);
          }
          this.entities = [... this.entities]
        })
        break
      case 'home':
        this.homeService.deleteHome(service.id).subscribe((data) => {
          let index = this.entities.indexOf(service);
          if (index !== -1) {
            this.entities.splice(index, 1);
          }
          this.entities = [... this.entities]
        })
        break
    }
  }

}
