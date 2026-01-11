import { inject } from "@angular/core";
import { CanActivateFn, Router } from "@angular/router";
import { TokenService } from "../auth/token.service";

export const CustomerGuard: CanActivateFn=()=>{
    const tokenSeervice=inject(TokenService);
    const router=inject(Router);

    const role=tokenSeervice.getRole();
    if(role!=='CUSTOMER'){
        router.navigate(['/login']);
        return false;
    }
    return true;
};