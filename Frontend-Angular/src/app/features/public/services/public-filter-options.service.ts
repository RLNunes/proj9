import { Injectable, inject } from '@angular/core';
import { Observable, map } from 'rxjs';

import { ApiService } from '../../../core/services';
import { FilterFieldOption } from '../../../shared/models';
import { KeywordApiResponse, ThemeApiResponse } from '../models';
import {
  mapKeywordApiResponseToFilterOptions,
  mapThemeApiResponseToFilterOptions,
} from '../mappers';

@Injectable({
  providedIn: 'root',
})
export class PublicFilterOptionsService {
  private readonly apiService = inject(ApiService);
  private readonly THEME_OPTIONS_BASE_PATH = 'tema/all';
  private readonly KEYWORD_OPTIONS_BASE_PATH = 'palavra_chave/all';

  getThemeOptions(
    pageNum = 1,
    rowsPage = 1000,
  ): Observable<FilterFieldOption[]> {
    return this.apiService
      .get<ThemeApiResponse>(`${this.THEME_OPTIONS_BASE_PATH}/${pageNum}/${rowsPage}`)
      .pipe(map(mapThemeApiResponseToFilterOptions));
  }

  getKeywordOptions(
    pageNum = 1,
    rowsPage = 1000,
  ): Observable<FilterFieldOption[]> {
    return this.apiService
      .get<KeywordApiResponse>(`${this.KEYWORD_OPTIONS_BASE_PATH}/${pageNum}/${rowsPage}`)
      .pipe(map(mapKeywordApiResponseToFilterOptions));
  }
}
