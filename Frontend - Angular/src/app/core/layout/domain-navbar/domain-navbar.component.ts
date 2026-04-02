import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NavigationCard } from '../../../shared/models/navigation-card.model';
@Component({
  selector: 'app-domain-navbar',
  imports: [RouterLink],
  templateUrl: './domain-navbar.component.html',
})
export class DomainNavbarComponent {
  @Input() title = '';
  @Input() links: NavigationCard[] = [];
}
