import {Component, ViewChild} from '@angular/core';
import { AvatarModule } from 'primeng/avatar';
import {LoginModalComponent} from '../../auth/login-modal/login-modal.component';
@Component({
  selector: 'app-user-menu',
  imports: [AvatarModule, LoginModalComponent],
  templateUrl: './user-menu.component.html',
})
export class UserMenuComponent {
  @ViewChild(LoginModalComponent) loginModal?: LoginModalComponent;

  onAvatarClick(): void {
    // se não autenticado
    this.loginModal?.open();
  }
}
