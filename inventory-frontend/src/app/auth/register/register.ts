import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';
  role = 'CUSTOMER';
  errorMessage='';
  successMessage='';

  constructor(
    private authService:AuthService,
    private router: Router
  ) {}
  register(){
    this.errorMessage='';
    this.successMessage='';
    this.authService.register(this.name,this.email,this.password,this.role).subscribe({
      next:()=>{
        this.successMessage="Registration Successful,Please Login";
        setTimeout(()=>{
          this.router.navigate(['/login']);
        },1500);
      },
      error:(err)=>{
        this.errorMessage=err.error?.message || 'Registration Failed';
      }
    });
  }
  goToLogin() {
    this.router.navigate(['/login']);
  }
}
