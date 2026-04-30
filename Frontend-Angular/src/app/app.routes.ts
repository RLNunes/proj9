import { Routes } from '@angular/router';
import { homeRoutes } from './features/home/home.routes';
import { adminRoutes } from './features/admin/admin.routes';
import { consultaRoutes } from './features/consulta/consulta.routes';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: '',
    children: [...homeRoutes, ...adminRoutes, ...consultaRoutes],
  },
  { path: '**', redirectTo: 'home' },
];
