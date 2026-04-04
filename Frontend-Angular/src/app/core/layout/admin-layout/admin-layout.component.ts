import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DomainNavbarComponent } from '../domain-navbar/domain-navbar.component';
import { DomainSearchbarComponent } from '../domain-searchbar/domain-searchbar.component';
import { NavigationCard } from '../../../shared/models/navigation-card.model';
@Component({
  selector: 'app-admin-layout',
  imports: [RouterOutlet, DomainNavbarComponent, DomainSearchbarComponent],
  templateUrl: './admin-layout.component.html',
})
export class AdminLayoutComponent {
  navLinks: NavigationCard[] = [
    { label: 'Pagina Inicial', description: 'Página inicial do domínio', link: '/admin' },
    { label: 'Toponímia', description: 'Gestão de toponímia', link: '/admin/toponimia' },
    { label: 'Marcadores', description: 'Marcadores temáticos', link: '/admin/marcadores' },
    { label: 'Ofícios-Títulos', description: 'Ofícios e títulos', link: '/admin/oficios-titulos' },
    { label: 'Secretário-Conselheiro', description: 'Secção de secretário-conselheiro', link: '/admin/secretario-conselheiro' },
    { label: 'Temas', description: 'Temas e agregadores', link: '/admin/temas' },
  ];
}
