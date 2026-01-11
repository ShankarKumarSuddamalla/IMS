import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { TokenService } from '../token.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  email = '';
  password = '';
  errorMessage='';
  constructor(
    private authService:AuthService,
    private tokenService:TokenService,
    private router: Router
  ) {}
  login(){
    this.errorMessage='';
    this.authService.login(this.email,this.password).subscribe({
      next: (res)=>{
        this.tokenService.saveToken(res.token);
        const role=this.tokenService.getRole();
        if(role==='ADMIN'){
          this.router.navigate(['/admin/dashboard']);
        }
        else if(role==='CUSTOMER'){
          this.router.navigate(['/customer/products']);
        }
        else if(role==='SUPPLIER'){
          this.router.navigate(['/supplier/dashboard']);
        }
        else{
          this.errorMessage='Invalid Role';
          this.tokenService.clear();
        }
      },
      error:(err)=>{
        this.errorMessage=err.error?.message || "Login Failed";
      }
    });
  }
  goToRegister() {
    this.router.navigate(['/register']);
  }
}
