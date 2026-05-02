import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { PublicFilterOptionsService } from './public-filter-options.service';

describe('PublicFilterOptionsService', () => {
  let service: PublicFilterOptionsService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting(), PublicFilterOptionsService],
    });

    service = TestBed.inject(PublicFilterOptionsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should load theme options from the legacy Tema endpoint', () => {
    service.getThemeOptions().subscribe((options) => {
      expect(options).toEqual([
        { label: 'Tema A', value: 10 },
      ]);
    });

    const request = httpMock.expectOne('/api/CircPeticionario/webresources/tema/all/1/1000');
    expect(request.request.method).toBe('GET');

    request.flush({
      allTemas: [{ pk_tema: '10', tema: 'Tema A' }],
      rowcount: 1,
      pageNum: 1,
      rowsPage: 1000,
    });
  });

  it('should load keyword options from the legacy Palavra-Chave endpoint', () => {
    service.getKeywordOptions().subscribe((options) => {
      expect(options).toEqual([
        { label: 'Palavra A', value: 20 },
      ]);
    });

    const request = httpMock.expectOne('/api/CircPeticionario/webresources/palavra_chave/all/1/1000');
    expect(request.request.method).toBe('GET');

    request.flush({
      allPalChaves: [{ pk_palavra_chave: '20', palavra_chave: 'Palavra A' }],
      rowcount: 1,
      pageNum: 1,
      rowsPage: 1000,
    });
  });
});
