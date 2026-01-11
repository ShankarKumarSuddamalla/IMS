import { HttpInterceptorFn } from "@angular/common/http";
import { inject } from "@angular/core";
import { TokenService } from "../auth/token.service";
import { Router } from "@angular/router";

export const jwtInterceptor:HttpInterceptorFn=(req,next)=>{
    const tokenService=inject(TokenService);
    const router=inject(Router);
    const token=tokenService.getToken();

    if(
        req.url.includes('/auth-service/auth/login') ||
        req.url.includes('/auth-service/auth/register')
    ){
        return next(req);
    }

    if(token){
        const authReq=req.clone({
            setHeaders:{
                Authorization:`Bearer ${token}`
            }
        });
        return next(authReq);
    }
    return next(req);
}