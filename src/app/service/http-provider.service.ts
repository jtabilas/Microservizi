import { Injectable } from '@angular/core';
import {ShopApiService} from "./shop-api.service";
import {ObjectUnsubscribedError, Observable} from "rxjs";

// path API
let pathSignup = "http://localhost:8010/api/auth/singup"
let pathLogin = "http://localhost:8010/api/auth/login"

@Injectable({
  providedIn: 'root'
})
export class HttpProviderService {

  constructor(private auth: ShopApiService ) { }

  // POST for signUp
  public signUp(model : any) : Observable<any> {
    return this.auth.post(pathSignup,model);
  }

  //POST for login
  public logIn(model : any) : Observable<any> {
    return this.auth.post(pathLogin, model)
  }

}
