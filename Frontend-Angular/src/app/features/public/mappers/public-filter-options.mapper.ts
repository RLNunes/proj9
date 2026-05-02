import { FilterFieldOption } from '../../../shared/models';
import { ThemeApiResponse } from '../models';

export function mapThemeApiResponseToFilterOptions(
  response: ThemeApiResponse,
): FilterFieldOption[] {
  return response.allTemas.map((theme) => ({
    label: theme.tema,
    value: Number(theme.pk_tema),
  }));
}
