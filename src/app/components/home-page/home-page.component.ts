import {Component, OnInit} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {HttpProviderService} from "../../service/http-provider.service";


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit{


  constructor(private httpProvider: HttpProviderService) {}

  ngOnInit() {
    this.getAllProducts();
  }

  dtoProduct: any[] = [];


  getAllProducts() {
    this.httpProvider.getAllProducts().subscribe({
      next: (response) => {
        //estraggo tutti i prodotti
        for (let i = 0; i < response.body.length; i++) {
          this.dtoProduct[i] = response.body[i];
          console.log("prodotti -> ", this.dtoProduct[i]);
        }

      },
      error: (error) => {
        console.log(error)
      }
    })
  }

}
