import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminUserService } from '../services/admin-user.service';
import { User } from '../models/user.model';

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customers.html',
  styleUrls: ['./customers.css']
})
export class CustomersComponent implements OnInit {

  customers: User[] = [];
  searchEmail = '';
  loading = false;
  error = '';

  constructor(private userService: AdminUserService) {}

  ngOnInit() {
    this.loadCustomers();
  }

  loadCustomers() {
    this.loading = true;
    this.userService.getCustomers().subscribe({
      next: (data) => {
        this.customers = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Failed to load customers';
        this.loading = false;
      }
    });
  }

  search() {
    if (!this.searchEmail) {
      this.loadCustomers();
      return;
    }

    this.loading = true;
    this.userService.searchByEmail(this.searchEmail).subscribe({
      next: (data) => {
        this.customers = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Customer not found';
        this.loading = false;
      }
    });
  }
}