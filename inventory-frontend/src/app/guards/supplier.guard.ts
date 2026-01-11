import { inject } from "@angular/core";
import { CanActivateFn, Router } from "@angular/router";
import { TokenService } from "../auth/token.service";

export const SupplierGuard: CanActivateFn=()=>{
    const tokenSeervice=inject(TokenService);
    const router=inject(Router);

    const role=tokenSeervice.getRole();
    if(role!=='SUPPLIER'){
        router.navigate(['/login']);
        return false;
    }
    return true;
};