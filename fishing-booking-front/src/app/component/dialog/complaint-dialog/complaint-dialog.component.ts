import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-complaint-dialog',
  templateUrl: './complaint-dialog.component.html',
  styleUrls: ['./complaint-dialog.component.scss']
})
export class ComplaintDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ComplaintDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  content: string = "";

  ngOnInit(): void {
  }
  send(): void{
    this.dialogRef.close(this.content);
  }

}
