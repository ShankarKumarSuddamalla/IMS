import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  email = '';
  password = '';
  role = 'CUSTOMER';

  constructor(private authService: AuthService) {}

  register(): void {
    this.authService.register({
      email: this.email,
      password: this.password,
      role: this.role
    }).subscribe({
      next: () => alert('Registration successful'),
      error: () => alert('Registration failed')
    });
  }
}
