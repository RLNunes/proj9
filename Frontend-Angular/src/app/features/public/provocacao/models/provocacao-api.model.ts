export interface ProvocacaoApiResponse {
  allProvocacao: ProvocacaoApiItem[];
  rowcount: number;
  pageNum?: number;
  rowsPage?: number;
}

export interface ProvocacaoApiItem {
  pk_provocacao: string;
  destinatario_autoridade_tratamento: string;
  resumo: string;
  referencia_documental: string;
  pescita: string;
  pesreq: string;
  palcha: string;
  tema: string;
  ano: string;
}
