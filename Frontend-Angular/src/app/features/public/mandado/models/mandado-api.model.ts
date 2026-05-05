export interface MandadoApiResponse {
  allMandados: MandadoApiItem[];
  rowcount: number;
  pageNum?: number;
  rowsPage?: number;
}

export interface MandadoApiItem {
  pk_mandado: string;
  referencia_documental: string;
  registro: string;
  resumo: string;
  pescita: string;
  pesreq: string;
  palcha: string;
  tema: string;
  ano: string;
}
