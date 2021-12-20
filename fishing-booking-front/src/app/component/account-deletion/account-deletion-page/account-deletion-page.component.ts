import { Component, OnInit } from '@angular/core';
import { AccountDeletion } from 'src/app/model/AccountDeletion';
import { AccountDeletionService } from 'src/app/service/account-deletion.service';

@Component({
  selector: 'app-account-deletion-page',
  templateUrl: './account-deletion-page.component.html',
  styleUrls: ['./account-deletion-page.component.scss']
})
export class AccountDeletionPageComponent implements OnInit {

  constructor(private accountDeletionsService: AccountDeletionService) { }

  accountDeletions: any
  ngOnInit(): void {
    this.accountDeletionsService.getAccountDeletions().subscribe((data: any) => {
      this.accountDeletions = data
    })
  }

  removeAccount(accountDeletion: AccountDeletion) {
    let index = this.accountDeletions.indexOf(accountDeletion);
      if (index !== -1) {
        this.accountDeletions.splice(index, 1);
      }
  }

}
