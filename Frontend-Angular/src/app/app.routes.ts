import { Routes } from '@angular/router';
import { ShellComponent } from './core/layout/shell/shell.component';
import { homeRoutes } from './features/home/home.routes';
import { adminRoutes } from './features/admin/admin.routes';
import { consultaRoutes } from './features/consulta/consulta.routes';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'inicio' },
  {
    path: '',
    component: ShellComponent,
    children: [...homeRoutes, ...adminRoutes, ...consultaRoutes],
  },
  { path: '**', redirectTo: 'inicio' },
];
