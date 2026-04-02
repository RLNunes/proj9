import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UserMenuComponent } from '../user-menu/user-menu.component';
@Component({
  selector: 'app-global-header',
  imports: [RouterLink, UserMenuComponent],
  templateUrl: './global-header.component.html',
})
export class GlobalHeaderComponent {
}
