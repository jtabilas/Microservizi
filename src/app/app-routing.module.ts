import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {HomePageComponent} from "./components/home-page/home-page.component";

// vari path
const routes: Routes = [
  {path:'',redirectTo: 'login', pathMatch: 'full'},
  {path:'login', component:LoginComponent},
  {path:'signup', component:RegisterComponent},
  {path:'home', component:HomePageComponent}
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
