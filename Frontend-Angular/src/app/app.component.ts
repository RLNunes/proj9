import {Component, inject, OnInit} from '@angular/core';
import {ShellComponent} from './core/layout';
import {AuthService} from './core/auth/services';

@Component({
  selector: 'app-root',
  imports: [ShellComponent],
  template: `
    <app-shell/>
  `,
})
export class AppComponent implements OnInit {
  private readonly authService = inject(AuthService);

  ngOnInit(): void {
    this.authService.restoreSession().subscribe();
  }
}

