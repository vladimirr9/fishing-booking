import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class StorageService {


  constructor() { }
  storeTokenData(token: string) : void {
    localStorage.setItem("token", token)
    let decoded = jwt_decode(token)
    console.log(decoded)
  }
}
