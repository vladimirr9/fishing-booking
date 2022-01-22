import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DeleteAccountService } from 'src/app/service/delete-account.service';
import { StorageService } from 'src/app/service/storage.service';
import { UserService } from 'src/app/service/user.service';
import { ChangePasswordComponent } from '../../dialog/change-password/change-password.component';
import { DeleteAccountComponent } from '../../dialog/delete-account/delete-account.component';

@Component({
  selector: 'app-home-owner-profile',
  templateUrl: './home-owner-profile.component.html',
  styleUrls: ['./home-owner-profile.component.scss']
})
export class HomeOwnerProfileComponent implements OnInit {

  constructor(private storageService: StorageService,
    private userService: UserService,
    private dialog: MatDialog,
    private deleteAccountService : DeleteAccountService) { }

    username!: string;
    deletionPending = false

  ngOnInit(): void {
    this.username = this.storageService.getUsername()
  }

  deleteAccount() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true
    dialogConfig.autoFocus = true
 
    const dialogRef = this.dialog.open(DeleteAccountComponent, dialogConfig);
 
    dialogRef.afterClosed().subscribe(
      res => {
        if (res) {
          let accountDeletion = {
            email: this.storageService.getUsername(),
            reason: res.reason
          }
          this.deleteAccountService.sendRequest(accountDeletion).subscribe((result) => {
            this.deletionPending = true
          })
        }
      }
 
    );
  }
  cancelDeletion() {
    this.deleteAccountService.cancelRequest(this.username).subscribe((result) => {
      this.deletionPending = false
    })
  }

  changePassword() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(ChangePasswordComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.userService.changePassword(this.username,data).subscribe((result:any) => {
            void(0) //observables are lazy if nothing is done it won't be executed
          })
        }
      }
    );
  }

}
