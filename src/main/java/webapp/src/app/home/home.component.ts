import { Component, OnInit } from '@angular/core';
import { Customer } from '../model/Customer';
import { CustomerService } from '../service/Customer.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  customerList : Customer[] = [];

  constructor(private customerService : CustomerService) { }

  ngOnInit(): void {
    this.getData();
  }

  getData(){
    this.customerService.getAllCustomers().subscribe(response =>{
      this.customerList = response;
      console.log(this.customerList);
    },err=>{});
  }

}
