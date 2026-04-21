import { Injectable, computed, inject, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { ApiService } from '../../services/api.service';
import { AuthUser, LoginRequest } from '../models';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly api = inject(ApiService);

  private readonly currentUserSignal = signal<AuthUser | null>(null);
  private readonly isAuthenticatedSignal = signal(false);

  readonly currentUser = computed(() => this.currentUserSignal());
  readonly isAuthenticated = computed(() => this.isAuthenticatedSignal());

  login(payload: LoginRequest): Observable<AuthUser> {
    return this.api.post<AuthUser>('auth/login', payload).pipe(
      tap((user) => {
        this.currentUserSignal.set(user);
        this.isAuthenticatedSignal.set(true);
      }),
    );
  }

  logout(): Observable<void> {
    return this.api.post<void>('auth/logout', {}).pipe(
      tap(() => {
        this.clearSession();
      }),
    );
  }

  fetchAuthenticatedUser(): Observable<AuthUser> {
    return this.api.get<AuthUser>('auth/me').pipe(
      tap((user) => {
        this.currentUserSignal.set(user);
        this.isAuthenticatedSignal.set(true);
      }),
    );
  }

  clearSession(): void {
    this.currentUserSignal.set(null);
    this.isAuthenticatedSignal.set(false);
  }
}
