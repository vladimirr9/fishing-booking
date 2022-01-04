import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProviderRegistrationService } from 'src/app/service/provider-registration.service';

@Component({
  selector: 'app-deny-registration-dialog',
  templateUrl: './deny-registration-dialog.component.html',
  styleUrls: ['./deny-registration-dialog.component.scss']
})
export class DenyRegistrationDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<DenyRegistrationDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private providerRegistrationService: ProviderRegistrationService) { }

  form = new FormGroup({
    reason: new FormControl('', Validators.required)
  })

  ngOnInit(): void {
  }

  close() {
    this.dialogRef.close();
  }
  deny() {
    this.providerRegistrationService.deny(this.data.id, this.form.controls['reason'].value).subscribe((data) => {
      this.dialogRef.close(true);
    })

  }

}
