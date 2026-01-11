import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './landing.html',
  styleUrls: ['./landing.css']
})
export class LandingComponent {
  constructor(private router:Router){}
  scrollTo(sectionId:string){
    document.getElementById(sectionId)?.scrollIntoView({
      behavior:'smooth'
    });
  }
  goToLogin(){
    this.router.navigate(['/login'])
  }
}
