import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { StorageService } from 'src/app/service/storage.service';
import { UserService } from 'src/app/service/user.service';
import { ChangePasswordComponent } from '../../dialog/change-password/change-password.component';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.scss']
})
export class ClientProfileComponent implements OnInit {

  constructor(private storageService: StorageService,
    private userService: UserService,
    private dialog: MatDialog) { }
    loyaltyPoints: number = 0;


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
