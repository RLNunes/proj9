import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UserMenuComponent } from '../user-menu/user-menu.component';
import { ToolbarModule } from 'primeng/toolbar';

@Component({
  selector: 'app-global-header',
  imports: [RouterLink, UserMenuComponent, ToolbarModule],
  templateUrl: './global-header.component.html',
})
export class GlobalHeaderComponent {
}
