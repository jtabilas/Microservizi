import { Injectable } from '@angular/core';
import {ShopApiService} from "./shop-api.service";
import {ObjectUnsubscribedError, Observable} from "rxjs";

// path API
let pathSignup = "http://localhost:8010/api/auth/singup"
let pathLogin = "http://localhost:8010/api/auth/login"
let pathGetProducts = "http://localhost:8010/api/products"
@Injectable({
  providedIn: 'root'
})
export class HttpProviderService {

  constructor(private shop: ShopApiService ) { }

  // POST for signUp
  public signUp(model : any) : Observable<any> {
    return this.shop.post(pathSignup,model);
  }

  //POST for login
  public logIn(model : any) : Observable<any> {
    return this.shop.post(pathLogin, model)
  }

  //GET all products
  public getAllProducts(): Observable<any> {
    return this.shop.get(pathGetProducts);
  }



}
