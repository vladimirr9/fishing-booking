import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { StorageService } from 'src/app/service/storage.service';
import { UserService } from 'src/app/service/user.service';
import { ChangePasswordComponent } from '../../dialog/change-password/change-password.component';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.scss']
})
export class AdminProfileComponent implements OnInit {

  constructor(private storageService: StorageService,
    private userService: UserService,
    private dialog: MatDialog) { }

 username!: string;


 ngOnInit(): void {
   this.username = this.storageService.getUsername()
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
