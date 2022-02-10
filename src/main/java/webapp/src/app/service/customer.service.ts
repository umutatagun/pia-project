import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn : 'root'
})
export class CustomerService{
    private uri = 'http://localhost:8080/customer';
    constructor(private http : HttpClient){}
    getAllCustomers(): Observable<any>{
        return this.http.get(this.uri);
    }
}