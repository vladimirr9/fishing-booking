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

  getAllUnapproved() {
    return this.http.get(`${config.baseUrl}${this.reviewUrl}/unapproved`);
  }

  approveReview(id: number) {
    return this.http.put(`${config.baseUrl}${this.reviewUrl}/${id}/approve`, {});
  }

  denyReview(id: number) {
    return this.http.put(`${config.baseUrl}${this.reviewUrl}/${id}/deny`, {});
  }
}
