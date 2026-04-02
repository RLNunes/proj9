import { Routes } from '@angular/router';
import { ConsultaLayoutComponent } from '../../core/layout/consulta-layout/consulta-layout.component';
import { InicioComponent as ConsultaInicioComponent } from './inicio/inicio.component';
import { PessoaComponent } from './pessoa/pessoa.component';
import { TemaComponent } from './tema/tema.component';
import { PalavraChaveComponent } from './palavra-chave/palavra-chave.component';
export const consultaRoutes: Routes = [
  {
    path: 'consulta',
    component: ConsultaLayoutComponent,
    children: [
      { path: '', redirectTo: 'inicio', pathMatch: 'full' },
      { path: 'inicio', component: ConsultaInicioComponent },
      { path: 'pessoa', component: PessoaComponent },
      { path: 'tema', component: TemaComponent },
      { path: 'palavra-chave', component: PalavraChaveComponent },
    ],
  },
];
