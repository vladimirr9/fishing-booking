import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AccountDeletion } from 'src/app/model/AccountDeletion';
import { AccountDeletionDialogComponent } from '../../dialog/account-deletion-dialog/account-deletion-dialog.component';
import { ChangePasswordComponent } from '../../dialog/change-password/change-password.component';

@Component({
  selector: 'app-account-deletion-card',
  templateUrl: './account-deletion-card.component.html',
  styleUrls: ['./account-deletion-card.component.scss']
})
export class AccountDeletionCardComponent implements OnInit {

  constructor(private dialog: MatDialog) { }

  @Input() accountDeletion !: AccountDeletion
  @Output() accountDeleted = new EventEmitter()
  ngOnInit(): void {
  }

  view() {

    const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true
        dialogConfig.autoFocus = true
        dialogConfig.data = this.accountDeletion

        const dialogRef = this.dialog.open(AccountDeletionDialogComponent, dialogConfig);

        dialogRef.afterClosed().subscribe(
          res => {
            if (res) {
              this.accountDeleted.emit(this.accountDeletion)
            }
          }
        );
  }

}
