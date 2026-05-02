import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

import { ApiService } from '../../../../core/services';
import { Provocacao, ProvocacaoApiResponse } from '../models';
import {mapProvocacaoApiResponseToProvocacoes} from '../mappers';

@Injectable({
  providedIn: 'root',
})
export class ProvocacaoService {
  private readonly apiService = inject(ApiService);

  private readonly PROVOCACAO_BASE_PATH = 'provocacao';

  getProvocacoes(pageNum = 1, rowsPage = 10): Observable<Provocacao[]> {
    return this.apiService
      .get<ProvocacaoApiResponse>(
        `${this.PROVOCACAO_BASE_PATH}/all/${pageNum}/${rowsPage}`,
      )
      .pipe(map(mapProvocacaoApiResponseToProvocacoes));
  }
}
