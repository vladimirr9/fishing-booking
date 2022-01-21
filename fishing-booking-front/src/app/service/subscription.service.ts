import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from 'src/shared';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private subscribtionUrl="/subscribe"
  constructor(private http: HttpClient) { }

  createBoatSubscription(userEmail: string,boatId: number){
    return this.http.post(`${config.baseUrl}${this.subscribtionUrl}/boat/${userEmail}/${boatId}`,{});
  }

  createHomeSubscription(userEmail: string,boatId: number){
    return this.http.post(`${config.baseUrl}${this.subscribtionUrl}/home/${userEmail}/${boatId}`,{});
  }


  createAdventureSubscription(userEmail: string,boatId: number){
    return this.http.post(`${config.baseUrl}${this.subscribtionUrl}/adventure/${userEmail}/${boatId}`,{});
  }

  deleteBoatSubscription(userEmail: string,boatId: number){
    return this.http.delete(`${config.baseUrl}${this.subscribtionUrl}/boat/${userEmail}/${boatId}`,{});
  }

  deleteHomeSubscription(userEmail: string,boatId: number){
    return this.http.delete(`${config.baseUrl}${this.subscribtionUrl}/home/${userEmail}/${boatId}`,{});
  }

  deleteAdventureSubscription(userEmail: string,boatId: number){
    return this.http.delete(`${config.baseUrl}${this.subscribtionUrl}/adventure/${userEmail}/${boatId}`,{});
  }

  isHomeSubscribed(userEmail: string,boatId: number): Observable<boolean>{
     return this.http.get<boolean>(`${config.baseUrl}${this.subscribtionUrl}/home/${userEmail}/${boatId}`,{});
  }

  isAdventureSubscribed(userEmail: string,boatId: number): Observable<boolean>{
    return this.http.get<boolean>(`${config.baseUrl}${this.subscribtionUrl}/adventure/${userEmail}/${boatId}`,{});
 }

 isBoatSubscribed(userEmail: string,boatId: number): Observable<boolean>{
  return this.http.get<boolean>(`${config.baseUrl}${this.subscribtionUrl}/boat/${userEmail}/${boatId}`,{});
}

  
}
