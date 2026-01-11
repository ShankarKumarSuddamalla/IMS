import { Component } from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { SidebarComponent } from "../sidebar/sidebar";

@Component({
    selector:'app-admin-layout',
    standalone:true,
    imports:[RouterOutlet,SidebarComponent],
    templateUrl:'./admin-layout.html',
    styleUrls:['./admin-layout.css']
})
export class AdminLayoutComponent{}