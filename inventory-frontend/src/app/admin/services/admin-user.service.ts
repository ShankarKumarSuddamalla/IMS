import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../models/user.model";
@Injectable({ providedIn: 'root'})
export class AdminUserService{
    private baseurl='http://localhost:8080/user-service/users';
    constructor(private http:HttpClient){}
    getCustomers():Observable<User[]>{
        return this.http.get<User[]>(`${this.baseurl}/customers`);
    }

    searchByEmail(email:string):Observable<User[]>{
        return this.http.get<User[]>(`${this.baseurl}/search?email=${email}`);
    }
    getSuppliers():Observable<User[]>{
        return this.http.get<User[]>(`${this.baseurl}/suppliers`);
    }
    deleteUser(id:number):Observable<void>{
        return this.http.delete<void>(`${this.baseurl}/${id}`)
    }
}