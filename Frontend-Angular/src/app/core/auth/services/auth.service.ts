import { Injectable, computed, inject, signal } from '@angular/core';
import { Observable, catchError, of, tap } from 'rxjs';

import { ApiService } from '../../services';
import { AuthUser, LoginRequest } from '../models';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly api = inject(ApiService);

  private readonly AUTH_LOGIN_PATH = 'auth/login';
  private readonly AUTH_LOGOUT_PATH = 'auth/logout';
  private readonly AUTH_ME_PATH = 'auth/me';

  private readonly currentUserSignal = signal<AuthUser | null>(null);
  private readonly isAuthenticatedSignal = signal(false);

  readonly currentUser = computed(() => this.currentUserSignal());
  readonly isAuthenticated = computed(() => this.isAuthenticatedSignal());

  login(payload: LoginRequest): Observable<AuthUser> {
    return this.api.post<AuthUser>(this.AUTH_LOGIN_PATH, payload).pipe(
      tap((user) => {
        this.setAuthenticatedUser(user);
      }),
    );
  }

  logout(): Observable<void> {
    return this.api.post<void>(this.AUTH_LOGOUT_PATH, {}).pipe(
      tap(() => {
        this.clearSession();
      }),
    );
  }

  fetchAuthenticatedUser(): Observable<AuthUser> {
    return this.api.get<AuthUser>(this.AUTH_ME_PATH).pipe(
      tap((user) => {
        this.setAuthenticatedUser(user);
      }),
    );
  }

  restoreSession(): Observable<AuthUser | null> {
    return this.fetchAuthenticatedUser().pipe(
      catchError(() => {
        this.clearSession();
        return of(null);
      }),
    );
  }

  clearSession(): void {
    this.currentUserSignal.set(null);
    this.isAuthenticatedSignal.set(false);
  }

  private setAuthenticatedUser(user: AuthUser): void {
    this.currentUserSignal.set(user);
    this.isAuthenticatedSignal.set(true);
  }
}
