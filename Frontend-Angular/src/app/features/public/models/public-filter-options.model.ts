import { FilterFieldOption } from '../../../shared/models';

export interface ThemeApiResponse {
  allTemas: ThemeApiItem[];
  rowcount: number;
  pageNum?: number;
  rowsPage?: number;
}

export interface ThemeApiItem {
  pk_tema: string;
  tema: string;
}

export type ThemeOption = FilterFieldOption;
