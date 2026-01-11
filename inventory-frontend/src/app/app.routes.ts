import { Routes } from '@angular/router';
import { LandingComponent } from './landing/landing';
import { LoginComponent } from './auth/login/login';
import { RegisterComponent } from './auth/register/register';
import { Component } from '@angular/core';
import { AdminGuard } from './guards/admin.guard';
import { AuthGuard } from './guards/auth.guard';
import { CustomerGuard } from './guards/customer.guard';
import { SupplierGuard } from './guards/supplier.guard';
import { DashboardComponent } from './admin/pages/dashboard';
import { AdminLayoutComponent } from './admin/layout/admin-layout';
import { CustomersComponent } from './admin/pages/customers';
import { SuppliersComponent } from './admin/pages/suppliers';
@Component({
  standalone:true,
  template:`<h2 style="padding:40px">Coming Soon</h2>`
})
class PlaceholderComponent{}
export const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { 
    path: 'admin',
    component:AdminLayoutComponent,
    canActivate:[AuthGuard,AdminGuard],
    children:[
      { path: 'dashboard', component: DashboardComponent },
      { path: 'customers', component: CustomersComponent },
      { path: 'suppliers', component: SuppliersComponent },
      { path: 'products', component: PlaceholderComponent },
      { path: 'orders', component: PlaceholderComponent },
      { path: 'reports', component: PlaceholderComponent },
      { path: 'profile', component: PlaceholderComponent }
    ]
  },
  { 
    path: 'customer/products',
    component:PlaceholderComponent,
    canActivate:[AuthGuard,CustomerGuard]
  },
  { 
    path: 'supplier/dashboard',
    component:PlaceholderComponent,
    canActivate:[AuthGuard,SupplierGuard]
  }
];
