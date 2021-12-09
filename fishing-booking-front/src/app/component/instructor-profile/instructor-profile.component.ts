import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-instructor-profile',
  templateUrl: './instructor-profile.component.html',
  styleUrls: ['./instructor-profile.component.scss']
})
export class InstructorProfileComponent implements OnInit {

  constructor(private storageService: StorageService) { }

  username!: string


  ngOnInit(): void {
    this.username = this.storageService.getUsername()
  }

}
