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
    { label: 'Pagina Inicial', description: 'Página inicial', link: '/home' },
    { label: 'Provocação', description: 'Área de provocação', link: '/home/provocacao' },
    { label: 'Mandado', description: 'Área de mandados', link: '/home/mandado' },
    { label: 'Consulta', description: 'Consulta pública', link: '/home/consulta' },
    { label: 'Ultramar', description: 'Conteúdo ultramarino', link: '/home/ultramar' },
    { label: 'Respostas', description: 'Respostas e seguimento', link: '/home/respostas' },
  ];
}
