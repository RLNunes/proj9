import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { finalize } from 'rxjs';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';

import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login-modal',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    DialogModule,
    InputTextModule,
    PasswordModule,
    CheckboxModule,
    ButtonModule,
  ],
  templateUrl: './login-modal.component.html',
})
export class LoginModalComponent {
  private readonly fb = inject(FormBuilder);
  private readonly authService = inject(AuthService);

  readonly visible = signal(false);
  readonly loading = signal(false);
  readonly errorMessage = signal<string | null>(null);

  readonly form = this.fb.nonNullable.group({
    username: ['', [Validators.required, Validators.maxLength(255)]],
    password: ['', [Validators.required]],
    rememberMe: [false],
  });

  open(): void {
    this.errorMessage.set(null);
    this.visible.set(true);
  }

  close(): void {
    this.visible.set(false);
    this.loading.set(false);
    this.errorMessage.set(null);
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const { username, password } = this.form.getRawValue();

    this.loading.set(true);
    this.errorMessage.set(null);

    this.authService
      .login({ username, password })
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe({
        next: () => {
          this.close();
        },
        error: () => {
          this.errorMessage.set('Credenciais inválidas.');
        },
      });
  }
}
