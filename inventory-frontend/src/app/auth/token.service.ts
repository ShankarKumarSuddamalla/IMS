import { Injectable } from "@angular/core";

@Injectable({providedIn:'root'})
export class TokenService{
    saveToken(token:string){
        localStorage.setItem('token',token);
    }
    getToken():string|null{
        return localStorage.getItem('token');
    }
    clear(){
        localStorage.clear();
    }
    getRole():string | null{
        const token=this.getToken();
        if(!token) return null;
        try{
            const payload=JSON.parse(atob(token.split('.')[1]));
            return payload.role;
        }
        catch{
            return null;
        }
    }
    getEmail():string |null{
        const token=this.getToken();
        if(!token) return null;
        try{
            const payload=JSON.parse(atob(token.split('.')[1]));
            return payload.sub;
        }
        catch{
            return null;
        }
    }
}