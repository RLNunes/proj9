import { Routes } from '@angular/router';
import { InicioComponent as ConsultaInicioComponent } from './inicio/inicio.component';
import { PessoaComponent } from './pessoa/pessoa.component';
import { TemaComponent } from './tema/tema.component';
import { PalavraChaveComponent } from './palavra-chave/palavra-chave.component';
import {ConsultaDomainShellComponent} from '../../core/layout';
export const consultaRoutes: Routes = [
  {
    path: 'consulta',
    component: ConsultaDomainShellComponent,
    children: [
      { path: '', component: ConsultaInicioComponent },
      { path: 'pessoa', component: PessoaComponent },
      { path: 'tema', component: TemaComponent },
      { path: 'palavra-chave', component: PalavraChaveComponent },
    ],
  },
];
