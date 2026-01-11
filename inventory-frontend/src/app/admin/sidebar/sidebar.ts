import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { TokenService } from "../../auth/token.service";

@Component({
    selector:'app-sidebar',
    standalone:true,
    imports:[CommonModule,RouterModule],
    templateUrl:'./sidebar.html',
    styleUrls:['./sidebar.css']
})

export class SidebarComponent{
    constructor(
        private router:Router,
        private tokenService:TokenService
    ){}
    logout(){
        this.tokenService.clear();
        this.router.navigate(['/']);
    }
}