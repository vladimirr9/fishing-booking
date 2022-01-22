import { Component, OnInit } from '@angular/core';
import { ReservationService } from 'src/app/service/reservation.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-income-report',
  templateUrl: './income-report.component.html',
  styleUrls: ['./income-report.component.scss']
})
export class IncomeReportComponent implements OnInit {

  constructor(private reservationService: ReservationService,
    private storageService: StorageService) { }

  income : number = 0
  fromTime : any 
  toTime : any

  ngOnInit(): void {
  }



  getIncome() : void {
    let incomeDto = {
      fromTime: this.fromTime,
      toTime: this.toTime,
      email: this.storageService.getUsername()
    }
    this.reservationService.incomeReport(incomeDto).subscribe((data:any) => {this.income = data});
  }

}
