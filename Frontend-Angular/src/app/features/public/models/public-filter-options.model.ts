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

export interface KeywordApiResponse {
  allPalChaves: KeywordApiItem[];
  rowcount: number;
  pageNum?: number;
  rowsPage?: number;
}

export interface KeywordApiItem {
  pk_palavra_chave: string;
  palavra_chave: string;
}

