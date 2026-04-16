import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { UserMenuComponent } from '../user-menu/user-menu.component';
import { ToolbarModule } from 'primeng/toolbar';
import { Avatar } from 'primeng/avatar';

@Component({
  selector: 'app-global-header',
  imports: [RouterLink, UserMenuComponent, ToolbarModule, Avatar],
  templateUrl: './global-header.component.html',
})
export class GlobalHeaderComponent {
}
