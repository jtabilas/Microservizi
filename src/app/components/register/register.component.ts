import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from "@angular/forms";
import {HttpProviderService} from "../../service/http-provider.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  ngOnInit(): void {
  }
  constructor(private httpProvider: HttpProviderService,
              private router: Router, private  toastr: ToastrService) {
  }

  AddSignUpForm: signupForm = new signupForm();

  @ViewChild("signupForm")
  signupForm!: NgForm;
  isSubmitted: boolean = false;

AddSignup() {
    this.isSubmitted = true;

    // Effettua la convalida dei campi del modulo
    if (this.isValidForm()) {
        this.httpProvider.signUp(this.AddSignUpForm).subscribe({
            next: (response) => {
                this.toastr.success('Register completed', 'Success');
                setTimeout(() => {
                    this.router.navigate(['/login']).then(r => null);
                }, 2500);
            },
            error: (error) => {
                this.toastr.error('Something is wrong', 'Error');
            }
        });
    }
}

isValidForm(): boolean {
    // Esegui la convalida dei campi del modulo qui
    if (!this.AddSignUpForm.name || !this.AddSignUpForm.city
      || !this.AddSignUpForm.surname || !this.AddSignUpForm.age || !this.AddSignUpForm.address || !this.AddSignUpForm.username
      || !this.AddSignUpForm.email || !this.AddSignUpForm.password)  {
        this.toastr.warning('Please fill in all fields', 'Warning' );
        return false;
    }
    // check dell'età
    else if (this.AddSignUpForm.age < 18) {
      this.toastr.warning('Only 18+', 'Warning');
      return false;
    }

    return true;
}

}

export class signupForm {
  name: string = '';
  surname: string = '';
  city: string = '';
  address: string = '';
  age: number= 0;
  username: string = '';
  email: string = '';
  password: string = '';
}
