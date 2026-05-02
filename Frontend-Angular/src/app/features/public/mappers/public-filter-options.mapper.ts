import { FilterFieldOption } from '../../../shared/models';
import { KeywordApiResponse, ThemeApiResponse } from '../models';

export function mapThemeApiResponseToFilterOptions(
  response: ThemeApiResponse,
): FilterFieldOption[] {
  return response.allTemas.map((theme) => ({
    label: theme.tema,
    value: Number(theme.pk_tema),
  }));
}

export function mapKeywordApiResponseToFilterOptions(
  response: KeywordApiResponse,
): FilterFieldOption[] {
  return response.allPalChaves.map((keyword) => ({
    label: keyword.palavra_chave,
    value: Number(keyword.pk_palavra_chave),
  }));
}

