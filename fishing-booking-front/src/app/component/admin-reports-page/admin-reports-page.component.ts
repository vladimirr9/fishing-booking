import { Component, OnInit } from '@angular/core';
import { ReportsService } from 'src/app/service/reports.service';

@Component({
  selector: 'app-admin-reports-page',
  templateUrl: './admin-reports-page.component.html',
  styleUrls: ['./admin-reports-page.component.scss']
})
export class AdminReportsPageComponent implements OnInit {

  reports: any
  constructor(private reportsService : ReportsService) { }

  ngOnInit(): void {
    this.reportsService.getUnapprovedReports().subscribe((data:any) => {
      this.reports = data
    })
  }

  viewReport(report: any) {

  }

}
