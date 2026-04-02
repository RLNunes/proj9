import { Routes } from '@angular/router';
import { AdminLayoutComponent } from '../../core/layout/admin-layout/admin-layout.component';
import { InicioComponent as AdminInicioComponent } from './inicio/inicio.component';
import { ToponimiaHomeComponent } from './toponimia/pages/toponimia-home/toponimia-home.component';
import { TermoComponent } from './toponimia/termo/termo.component';
import { FreguesiaComponent } from './toponimia/freguesia/freguesia.component';
import { ComarcaComponent } from './toponimia/comarca/comarca.component';
import { CapitaniaComponent } from './toponimia/capitania/capitania.component';
import { RegistoComponent } from './registo/registo.component';
import { ReferenciaDocumentalComponent } from './referencia-documental/referencia-documental.component';
import { MarcadoresHomeComponent } from './marcadores/pages/marcadores-home/marcadores-home.component';
import { EconomicoOcupacaoComponent } from './marcadores/economico-ocupacao/economico-ocupacao.component';
import { SocioJuridicoComponent } from './marcadores/socio-juridico/socio-juridico.component';
import { OficiosTitulosHomeComponent } from './oficios-titulos/pages/oficios-titulos-home/oficios-titulos-home.component';
import { OficiosTitulosComponent } from './oficios-titulos/oficios-titulos/oficios-titulos.component';
import { AgregadoresComponent as OficiosTitulosAgregadoresComponent } from './oficios-titulos/agregadores/agregadores.component';
import { SecretarioConselheiroHomeComponent } from './secretario-conselheiro/pages/secretario-conselheiro-home/secretario-conselheiro-home.component';
import { SecretarioConselheiroComponent } from './secretario-conselheiro/secretario-conselheiro/secretario-conselheiro.component';
import { AgregadoresComponent as SecretarioConselheiroAgregadoresComponent } from './secretario-conselheiro/agregadores/agregadores.component';
import { PessoaComponent } from './pessoa/pessoa.component';
import { TemasHomeComponent } from './temas/pages/temas-home/temas-home.component';
import { TemaComponent } from './temas/tema/tema.component';
import { AgregadoresComponent as TemasAgregadoresComponent } from './temas/agregadores/agregadores.component';
import { PalavraChaveComponent } from './palavra-chave/palavra-chave.component';
import { UtilizadorComponent } from './utilizador/utilizador.component';
export const adminRoutes: Routes = [
  {
    path: 'admin',
    component: AdminLayoutComponent,
    children: [
      { path: '', redirectTo: 'inicio', pathMatch: 'full' },
      { path: 'inicio', component: AdminInicioComponent },
      {
        path: 'toponimia',
        children: [
          { path: '', component: ToponimiaHomeComponent },
          { path: 'termo', component: TermoComponent },
          { path: 'freguesia', component: FreguesiaComponent },
          { path: 'comarca', component: ComarcaComponent },
          { path: 'capitania', component: CapitaniaComponent },
        ],
      },
      { path: 'registro', component: RegistoComponent },
      { path: 'referencia-documental', component: ReferenciaDocumentalComponent },
      {
        path: 'marcadores',
        children: [
          { path: '', component: MarcadoresHomeComponent },
          { path: 'economico-ocupacao', component: EconomicoOcupacaoComponent },
          { path: 'socio-juridico', component: SocioJuridicoComponent },
        ],
      },
      {
        path: 'oficios-titulos',
        children: [
          { path: '', component: OficiosTitulosHomeComponent },
          { path: 'oficios-titulos', component: OficiosTitulosComponent },
          { path: 'agregadores', component: OficiosTitulosAgregadoresComponent },
        ],
      },
      {
        path: 'secretario-conselheiro',
        children: [
          { path: '', component: SecretarioConselheiroHomeComponent },
          { path: 'secretario-conselheiro', component: SecretarioConselheiroComponent },
          { path: 'agregadores', component: SecretarioConselheiroAgregadoresComponent },
        ],
      },
      { path: 'pessoa', component: PessoaComponent },
      {
        path: 'temas',
        children: [
          { path: '', component: TemasHomeComponent },
          { path: 'tema', component: TemaComponent },
          { path: 'agregadores', component: TemasAgregadoresComponent },
        ],
      },
      { path: 'palavra-chave', component: PalavraChaveComponent },
      { path: 'utilizador', component: UtilizadorComponent },
    ],
  },
];
