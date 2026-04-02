import { Component } from '@angular/core';
import { GlobalHeaderComponent } from '../core/layout/global-header/global-header.component';
@Component({
  selector: 'app-header',
  imports: [GlobalHeaderComponent],
  templateUrl: './header.component.html',
})
export class HeaderComponent {}
