import {Component, ViewChild} from '@angular/core';
import {NgForm} from '@angular/forms';
import { Router } from '@angular/router';
import {HttpProviderService} from "../../service/http-provider.service";
import {ToastrService} from "ngx-toastr";
import {timeInterval, timeout} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  addLoginForm: loginForm = new loginForm();

  @ViewChild("loginForm")
  loginForm!: NgForm;
  isSubmitted: boolean = false;

  constructor(private httpProvider : HttpProviderService,
              private  router: Router, private toastr: ToastrService)  {}

  login() {
    this.isSubmitted = true;
    if (this.isValidForm()) {
      this.httpProvider.logIn(this.addLoginForm).subscribe(
        {
          next: (response) => {
            this.toastr.success('Login successfully', 'Success');
            // save my token on the localcStorage
            localStorage.setItem('accessToken', response.body.accessToken);
            localStorage.setItem('refreshToken', response.body.refreshToken);

            setTimeout(() => {
                this.router.navigate(['/home']).then(r => null);
              }, 2500)
          },
          error: (error) => {
            this.toastr.error('Username & Password not valid','Error')
          }
        }
      )
    }
  }


  isValidForm(): boolean {
    // Esegui la convalida dei campi del modulo qui
    if (!this.addLoginForm.username || !this.addLoginForm.password)  {
        this.toastr.warning('Please fill in all fields', 'Warning' );
        return false;
    }

    return true;
}

}

export class loginForm {
  username: string = '';
  password: string = '';
}
