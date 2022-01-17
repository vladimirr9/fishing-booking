import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateReviewDTO } from '../dto/CreateReviewDTO';
import { config } from "src/shared"

@Injectable({
  providedIn: 'root'
})
export class ReviewServiceService {

  private reviewUrl="/reviews"
  constructor(private http: HttpClient) { }

  createReview(reviewDto: CreateReviewDTO){
    return this.http.post(`${config.baseUrl}${this.reviewUrl}/create`,reviewDto);
  }
}
