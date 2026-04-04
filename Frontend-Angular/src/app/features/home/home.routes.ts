import { Routes } from '@angular/router';
import { HomeLayoutComponent } from '../../core/layout/home-layout/home-layout.component';
import { InicioComponent as HomeInicioComponent } from './inicio/inicio.component';
import { ProvocacaoComponent as HomeProvocacaoComponent } from './provocacao/provocacao.component';
import { MandadoComponent as HomeMandadoComponent } from './mandado/mandado.component';
import { ConsultaComponent as HomeConsultaComponent } from './consulta/consulta.component';
import { UltramarComponent as HomeUltramarComponent } from './ultramar/ultramar.component';
import { RespostasComponent as HomeRespostasComponent } from './respostas/respostas.component';
export const homeRoutes: Routes = [
  {
    path: 'home',
    component: HomeLayoutComponent,
    children: [
      { path: '', component: HomeInicioComponent },
      { path: 'provocacao', component: HomeProvocacaoComponent },
      { path: 'mandado', component: HomeMandadoComponent },
      { path: 'consulta', component: HomeConsultaComponent },
      { path: 'ultramar', component: HomeUltramarComponent },
      { path: 'respostas', component: HomeRespostasComponent },
    ],
  },
];
