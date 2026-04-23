import {Component, inject,  ViewChild} from '@angular/core';
import { AvatarModule } from 'primeng/avatar';
import {LoginModalComponent} from '../../auth/login-modal/login-modal.component';
import {RippleModule} from 'primeng/ripple';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/services/auth.service';
import { HeaderStateService } from '../../services/header-state.service';
import { PopoverModule } from 'primeng/popover';

@Component({
  selector: 'app-user-menu',
  imports: [AvatarModule, LoginModalComponent, PopoverModule, RippleModule],
  templateUrl: './user-menu.component.html',
})
export class UserMenuComponent {
  @ViewChild('op') overlayPanel?: any;
  @ViewChild(LoginModalComponent) loginModal?: LoginModalComponent;

  private readonly authService = inject(AuthService);
  private readonly headerStateService = inject(HeaderStateService);
  private readonly router = inject(Router);

  readonly isAuthenticated = this.authService.isAuthenticated;
  readonly currentUser = this.authService.currentUser;
  readonly dropdownDomains = this.headerStateService.dropdownDomains;

  onAvatarClick(event: MouseEvent): void {
    // se não autenticado
    //this.loginModal?.open();

    if (this.isAuthenticated()) {
      this.overlayPanel?.toggle(event);
    } else {
      this.loginModal?.open();
    }
  }

  navigateTo(link: string): void {
    this.overlayPanel?.hide();
    this.router.navigateByUrl(link);
  }

  logout(): void {
    this.overlayPanel?.hide();
    this.authService.logout().subscribe({
      next: () => this.router.navigateByUrl('/home'),
      error: () => {
        this.authService.clearSession();
        this.router.navigateByUrl('/home');
      },
    });
  }
}
