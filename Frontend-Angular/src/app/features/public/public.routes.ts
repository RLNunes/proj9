import {Routes} from '@angular/router';
import {PublicDomainShellComponent} from '../../core/layout';
import {UltramarComponent} from './ultramar/ultramar.component';
import {HomeComponent as PublicHomeComponent} from './home/home.component';
import {ProvocacaoComponent} from './provocacao/provocacao.component';
import {MandadoComponent} from './mandado/mandado.component';
import {ConsultaComponent} from './consulta/consulta.component';
import {RespostasComponent} from './respostas/respostas.component';

export const publicRoutes: Routes = [
  {
    path: 'home',
    component: PublicDomainShellComponent,
    children: [
      {path: '', component: PublicHomeComponent},
      {path: 'provocacao', component: ProvocacaoComponent},
      {path: 'mandado', component: MandadoComponent},
      {path: 'consulta', component: ConsultaComponent},
      {path: 'ultramar', component: UltramarComponent},
      {path: 'respostas', component: RespostasComponent},
    ],
  },
];
