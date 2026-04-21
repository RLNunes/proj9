import { Injectable, computed, inject, signal } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';

import { HEADER_CONFIG } from '../config/header.config';
import {
  HeaderConfig,
  HeaderDomain,
  HeaderDomainConfig,
} from '../models/header-config.model';
import { AuthService } from '../auth/services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class HeaderStateService {
  private readonly router = inject(Router);
  private readonly authService = inject(AuthService);

  private readonly currentUrl = signal(this.router.url);

  readonly currentDomain = computed<HeaderDomain>(() =>
    this.resolveDomain(this.currentUrl()),
  );

  readonly headerConfig = computed<HeaderConfig>(() => {
    const domain = this.currentDomain();
    const baseConfig = HEADER_CONFIG[domain];
    const url = this.currentUrl();
    const authenticated = this.authService.isAuthenticated();

    return this.buildHeaderConfig(baseConfig, url, authenticated);
  });

  constructor() {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event) => {
        this.currentUrl.set((event as NavigationEnd).urlAfterRedirects);
      });
  }

  private buildHeaderConfig(
    baseConfig: HeaderDomainConfig,
    url: string,
    isAuthenticated: boolean,
  ): HeaderConfig {
    return {
      ...baseConfig,
      displayNav: isAuthenticated,
      displaySearchbar: this.resolveDisplaySearchbar(
        baseConfig.domain,
        url,
        isAuthenticated,
      ),
    };
  }

  private resolveDomain(url: string): HeaderDomain {
    if (url.startsWith('/admin')) {
      return 'admin';
    }

    if (url.startsWith('/consulta')) {
      return 'consulta';
    }

    return 'home';
  }

  private resolveDisplaySearchbar(
    domain: HeaderDomain,
    url: string,
    isAuthenticated: boolean,
  ): boolean {
    if (!isAuthenticated) {
      return false;
    }

    if (domain === 'consulta') {
      return false;
    }

    if (domain === 'home') {
      return url !== '/home';
    }

    if (domain === 'admin') {
      return url !== '/admin';
    }

    return false;
  }
}
