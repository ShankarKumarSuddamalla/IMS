import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({providedIn:'root'})
export class AuthService{
    private baseurl='http://localhost:8080/auth-service/auth';
    constructor(private http:HttpClient){

    }
    login(email:string,password:string):Observable<any>{
        return this.http.post(`${this.baseurl}/login`,{
            email,
            password
        });
    }
    register(name:string,email:string,password:string,role:string){
        return this.http.post(`${this.baseurl}/register`,{
            name,
            email,
            password,
            role
        },
        {responseType:'text'}
    );
    }
}