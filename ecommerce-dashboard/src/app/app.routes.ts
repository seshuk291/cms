import { Routes } from '@angular/router';
import { Dashboard } from './dashboard/dashboard';
import { Users } from './users/users';
import { Orders } from './orders/orders';
import { Roles } from './roles/roles';
import { Transactions } from './transactions/transactions';
import { Categories } from './categories/categories';
import { Products } from './products/products';

export const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', component: Dashboard },
      { path: 'users', component: Users },
      { path: 'orders', component: Orders },
      { path: 'categories', component: Categories },
      { path: 'roles', component: Roles },
      { path: 'products', component: Products },
      { path: 'transactions', component: Transactions },
    ],
  },
];
