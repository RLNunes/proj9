import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NavigationCard } from '../../../shared/models/navigation-card.model';
@Component({
  selector: 'app-navigation-cards',
  imports: [RouterLink],
  templateUrl: './navigation-cards.component.html',
})
export class NavigationCardsComponent {
  @Input() cards: NavigationCard[] = [];
}
