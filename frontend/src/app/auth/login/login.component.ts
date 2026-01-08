import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { TokenService } from '../../core/services/token.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email = '';
  password = '';

  constructor(
    private authService: AuthService,
    private tokenService: TokenService
  ) {}

  login(): void {
    this.authService.login({
      email: this.email,
      password: this.password
    }).subscribe({
      next: res => {
        this.tokenService.saveToken(res.token);
        alert('Login successful');
      },
      error: () => alert('Login failed')
    });
  }
}

