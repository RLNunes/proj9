import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';

type RequestOptions = {
  headers?: HttpHeaders | Record<string, string | string[]>;
};

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private readonly API_BASE_PATH = '/api/CircPeticionario/webresources';
  private readonly LEGACY_TOKEN_HEADER = 'token';
  private readonly AUTH_PATH_PREFIX = 'auth/';

  constructor(private readonly http: HttpClient) {}

  get<T>(path: string, options: RequestOptions = {}): Observable<T> {
    return this.http.get<T>(
      this.buildUrl(path),
      this.withLegacyTokenHeader(path, options),
    );
  }

  post<T>(path: string, body: unknown, options: RequestOptions = {}): Observable<T> {
    return this.http.post<T>(
      this.buildUrl(path),
      body,
      this.withLegacyTokenHeader(path, options),
    );
  }

  put<T>(path: string, body: unknown, options: RequestOptions = {}): Observable<T> {
    return this.http.put<T>(
      this.buildUrl(path),
      body,
      this.withLegacyTokenHeader(path, options),
    );
  }

  delete<T>(path: string, options: RequestOptions = {}): Observable<T> {
    return this.http.delete<T>(
      this.buildUrl(path),
      this.withLegacyTokenHeader(path, options),
    );
  }

  private buildUrl(path: string): string {
    return `${this.API_BASE_PATH}/${this.normalizePath(path)}`;
  }

  private withLegacyTokenHeader(path: string, options: RequestOptions): RequestOptions {
    if (!this.shouldAttachLegacyToken(path)) {
      return options;
    }

    const legacyApiToken = environment.legacyApiToken.trim();

    if (!legacyApiToken) {
      return options;
    }

    const headers = this.toHttpHeaders(options.headers).set(
      this.LEGACY_TOKEN_HEADER,
      legacyApiToken,
    );

    return {
      ...options,
      headers,
    };
  }

  private shouldAttachLegacyToken(path: string): boolean {
    return !this.normalizePath(path).startsWith(this.AUTH_PATH_PREFIX);
  }

  private normalizePath(path: string): string {
    return path.replace(/^\/+/, '');
  }

  private toHttpHeaders(
    headers?: HttpHeaders | Record<string, string | string[]>,
  ): HttpHeaders {
    return headers instanceof HttpHeaders
      ? headers
      : new HttpHeaders(headers ?? {});
  }
}
