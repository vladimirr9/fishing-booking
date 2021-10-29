import { Injectable } from '@angular/core';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class StorageService {


  constructor() { }
  storeTokenData(token: string) : void {
    localStorage.setItem("token", token)
    let decoded: any = jwt_decode(token)
    localStorage.setItem("user", decoded.sub)
    localStorage.setItem("role", decoded.role)
    console.log(decoded)
  }
}
