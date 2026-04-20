import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ToolbarModule } from 'primeng/toolbar';
import { HeaderStateService } from '../../services/header-state.service';
import {UserMenuComponent} from '../user-menu/user-menu.component';

@Component({
  selector: 'app-global-header',
  imports: [RouterLink, ToolbarModule, UserMenuComponent],
  templateUrl: './global-header.component.html',
})
export class GlobalHeaderComponent {
  private readonly headerStateService = inject(HeaderStateService);

  readonly headerConfig = this.headerStateService.headerConfig;
}
