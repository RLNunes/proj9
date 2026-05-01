import { Routes } from '@angular/router';
import { publicRoutes } from './features/public/public.routes';
import { adminRoutes } from './features/admin/admin.routes';
import { consultaRoutes } from './features/consulta/consulta.routes';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: '',
    children: [...publicRoutes, ...adminRoutes, ...consultaRoutes],
  },
  { path: '**', redirectTo: 'home' },
];
