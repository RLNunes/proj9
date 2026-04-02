import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavigationCardsComponent } from '../navigation-cards/navigation-cards.component';
import { NavigationCard } from '../../../shared/models/navigation-card.model';
@Component({
  selector: 'app-home-layout',
  imports: [RouterOutlet, NavigationCardsComponent],
  templateUrl: './home-layout.component.html',
})
export class HomeLayoutComponent {
  cards: NavigationCard[] = [
    { label: 'Início', description: 'Página inicial', link: '/inicio' },
    { label: 'Provocação', description: 'Área de provocação', link: '/inicio/provocacao' },
    { label: 'Mandado', description: 'Área de mandados', link: '/inicio/mandado' },
    { label: 'Consulta', description: 'Consulta pública', link: '/inicio/consulta' },
    { label: 'Ultramar', description: 'Conteúdo ultramarino', link: '/inicio/ultramar' },
    { label: 'Respostas', description: 'Respostas e seguimento', link: '/inicio/respostas' },
  ];
}
