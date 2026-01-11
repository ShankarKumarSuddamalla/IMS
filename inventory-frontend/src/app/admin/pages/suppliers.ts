import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminUserService } from '../services/admin-user.service';
import { User } from '../models/user.model';

@Component({
  selector: 'app-suppliers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './suppliers.html',
  styleUrls: ['./suppliers.css']
})
export class SuppliersComponent implements OnInit {

  suppliers: User[] = [];
  searchEmail = '';
  loading = false;
  error = '';

  constructor(private userService: AdminUserService) {}

  ngOnInit() {
    this.loadSuppliers();
  }

  loadSuppliers() {
    this.loading = true;
    this.userService.getSuppliers().subscribe({
      next: (data) => {
        this.suppliers = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Failed to load suppliers';
        this.loading = false;
      }
    });
  }

  search() {
    if (!this.searchEmail) {
      this.loadSuppliers();
      return;
    }

    this.loading = true;
    this.userService.searchByEmail(this.searchEmail).subscribe({
      next: (data) => {
        this.suppliers = data;
        this.loading = false;
      },
      error: () => {
        this.error = 'Supplier not found';
        this.loading = false;
      }
    });
  }

  deleteSupplier(id: number) {
    if (!confirm('Are you sure you want to delete this supplier?')) {
      return;
    }

    this.userService.deleteUser(id).subscribe({
      next: () => {
        this.suppliers = this.suppliers.filter(s => s.id !== id);
      },
      error: () => {
        alert('Failed to delete supplier');
      }
    });
  }
}