import { HttpHeaders } from '@angular/common/http';
import { provideHttpClient } from '@angular/common/http';
import {
  HttpTestingController,
  provideHttpClientTesting,
} from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { environment } from '../../../environments/environment';
import { ApiService } from './api.service';

describe('ApiService', () => {
  let service: ApiService;
  let httpMock: HttpTestingController;
  let originalLegacyApiToken: string;

  beforeEach(() => {
    originalLegacyApiToken = environment.legacyApiToken;
    environment.legacyApiToken = 'legacy-token';

    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        ApiService,
      ],
    });

    service = TestBed.inject(ApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
    environment.legacyApiToken = originalLegacyApiToken;
  });

  it('should add the legacy token header to legacy GET requests', () => {
    service.get('tema/all/1/1000').subscribe();

    const request = httpMock.expectOne(
      '/api/CircPeticionario/webresources/tema/all/1/1000',
    );

    expect(request.request.method).toBe('GET');
    expect(request.request.headers.get('token')).toBe('legacy-token');

    request.flush({});
  });

  it('should add the legacy token header to legacy POST requests', () => {
    service.post('provocacao/search', { value: 'test' }).subscribe();

    const request = httpMock.expectOne(
      '/api/CircPeticionario/webresources/provocacao/search',
    );

    expect(request.request.method).toBe('POST');
    expect(request.request.headers.get('token')).toBe('legacy-token');

    request.flush({});
  });

  it('should add the legacy token header to legacy PUT requests', () => {
    service.put('provocacao/update/1', { id: 1 }).subscribe();

    const request = httpMock.expectOne(
      '/api/CircPeticionario/webresources/provocacao/update/1',
    );

    expect(request.request.method).toBe('PUT');
    expect(request.request.headers.get('token')).toBe('legacy-token');

    request.flush({});
  });

  it('should add the legacy token header to legacy DELETE requests', () => {
    service.delete('tema/delete/1').subscribe();

    const request = httpMock.expectOne(
      '/api/CircPeticionario/webresources/tema/delete/1',
    );

    expect(request.request.method).toBe('DELETE');
    expect(request.request.headers.get('token')).toBe('legacy-token');

    request.flush({});
  });

  it('should preserve existing headers when adding the legacy token header', () => {
    const headers = new HttpHeaders({
      'X-Test': 'test-value',
    });

    service.get('tema/all/1/1000', { headers }).subscribe();

    const request = httpMock.expectOne(
      '/api/CircPeticionario/webresources/tema/all/1/1000',
    );

    expect(request.request.headers.get('X-Test')).toBe('test-value');
    expect(request.request.headers.get('token')).toBe('legacy-token');

    request.flush({});
  });

  it('should preserve existing record headers when adding the legacy token header', () => {
    service
      .get('tema/all/1/1000', {
        headers: {
          'X-Test': 'test-value',
        },
      })
      .subscribe();

    const request = httpMock.expectOne(
      '/api/CircPeticionario/webresources/tema/all/1/1000',
    );

    expect(request.request.headers.get('X-Test')).toBe('test-value');
    expect(request.request.headers.get('token')).toBe('legacy-token');

    request.flush({});
  });

  it('should not add the legacy token header to auth endpoints', () => {
    service.post('auth/login', { username: 'user', password: 'pass' }).subscribe();

    const request = httpMock.expectOne(
      '/api/CircPeticionario/webresources/auth/login',
    );

    expect(request.request.method).toBe('POST');
    expect(request.request.headers.has('token')).toBeFalse();

    request.flush({});
  });

  it('should not add the legacy token header when the token is empty', () => {
    environment.legacyApiToken = '';

    service.get('tema/all/1/1000').subscribe();

    const request = httpMock.expectOne(
      '/api/CircPeticionario/webresources/tema/all/1/1000',
    );

    expect(request.request.headers.has('token')).toBeFalse();

    request.flush({});
  });
});
