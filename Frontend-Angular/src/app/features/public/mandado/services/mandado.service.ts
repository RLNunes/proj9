import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

import { ApiService } from '../../../../core/services';
import { mapMandadoApiItemToMandado, mapMandadoApiResponseToMandadoPage } from '../mappers';
import { Mandado, MandadoApiItem, MandadoApiResponse, MandadoPage, MandadoSearchFilters } from '../models';

@Injectable({
  providedIn: 'root',
})
export class MandadoService {
  private readonly apiService = inject(ApiService);

  private readonly MANDADO_BASE_PATH = 'mandado';

  getMandados(pageNum = 1, rowsPage = 10): Observable<MandadoPage> {
    return this.apiService
      .get<MandadoApiResponse>(
        `${this.MANDADO_BASE_PATH}/all/${pageNum}/${rowsPage}`,
      )
      .pipe(map(mapMandadoApiResponseToMandadoPage));
  }

  searchMandados(filters: MandadoSearchFilters): Observable<Mandado[]> {
    const search = encodeURIComponent(filters.search || 'all');
    const pessoa = encodeURIComponent(filters.pessoa || 'all');
    const tema = filters.tema || 0;
    const palavraChave = filters.palavraChave || 0;

    return this.apiService
      .get<MandadoApiItem[]>(
        `${this.MANDADO_BASE_PATH}/search/${search}/${pessoa}/${tema}/${palavraChave}`,
      )
      .pipe(map((items) => items.map(mapMandadoApiItemToMandado)));
  }
}
