import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

import { ApiService } from '../../../../core/services';
import {mapProvocacaoApiItemToProvocacao, mapProvocacaoApiResponseToProvocacaoPage} from '../mappers';
import {Provocacao, ProvocacaoApiItem, ProvocacaoApiResponse, ProvocacaoPage, ProvocacaoSearchFilters} from '../models';

@Injectable({
  providedIn: 'root',
})
export class ProvocacaoService {
  private readonly apiService = inject(ApiService);

  private readonly PROVOCACAO_BASE_PATH = 'provocacao';

  getProvocacoes(pageNum = 1, rowsPage = 10): Observable<ProvocacaoPage> {
    return this.apiService
      .get<ProvocacaoApiResponse>(
        `${this.PROVOCACAO_BASE_PATH}/all/${pageNum}/${rowsPage}`,
      )
      .pipe(map(mapProvocacaoApiResponseToProvocacaoPage));
  }

  searchProvocacoes(filters: ProvocacaoSearchFilters): Observable<Provocacao[]> {
    const search = encodeURIComponent(filters.search || 'all');
    const pessoa = encodeURIComponent(filters.pessoa || 'all');
    const tema = filters.tema || 0;
    const palavraChave = filters.palavraChave || 0;

    return this.apiService
      .get<ProvocacaoApiItem[]>(
        `${this.PROVOCACAO_BASE_PATH}/search/${search}/${pessoa}/${tema}/${palavraChave}`,
      )
      .pipe(map((items) => items.map(mapProvocacaoApiItemToProvocacao)));
  }
}
