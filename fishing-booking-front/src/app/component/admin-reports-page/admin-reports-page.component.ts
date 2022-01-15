import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ReportsService } from 'src/app/service/reports.service';
import { AdminReportDialogComponent } from '../dialog/admin-report-dialog/admin-report-dialog.component';
import { ReportDialogComponent } from '../dialog/report-dialog/report-dialog.component';

@Component({
  selector: 'app-admin-reports-page',
  templateUrl: './admin-reports-page.component.html',
  styleUrls: ['./admin-reports-page.component.scss']
})
export class AdminReportsPageComponent implements OnInit {

  reports: any
  constructor(private reportsService : ReportsService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.reportsService.getUnapprovedReports().subscribe((data:any) => {
      this.reports = data
    })
  }

  viewReport(report: any) {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      comment: report.comment,
      sanction: report.sanction,
      appeared: report.appeared
    }
    const dialogRef = this.dialog.open(AdminReportDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      res => {
        if (res) {
          this.reportsService.sendAdminReponse(report.reservation.id, report.id, res.status, {message: res.reason}).subscribe((data) => {
            this.reports = this.reports.filter((el: any) => el !== report);
          })
        }
      }
    );
  }

}
