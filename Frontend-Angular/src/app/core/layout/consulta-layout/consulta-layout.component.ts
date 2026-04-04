import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DomainNavbarComponent } from '../domain-navbar/domain-navbar.component';
import { NavigationCard } from '../../../shared/models/navigation-card.model';
@Component({
  selector: 'app-consulta-layout',
  imports: [RouterOutlet, DomainNavbarComponent],
  templateUrl: './consulta-layout.component.html',
})
export class ConsultaLayoutComponent {
  navLinks: NavigationCard[] = [
    { label: 'Pagina Inicial', description: 'Página inicial da consulta', link: '/consulta' },
    { label: 'Pessoa', description: 'Pesquisa por pessoa', link: '/consulta/pessoa' },
    { label: 'Tema', description: 'Pesquisa por tema', link: '/consulta/tema' },
    { label: 'Palavra-Chave', description: 'Pesquisa por palavra-chave', link: '/consulta/palavra-chave' },
  ];
}
