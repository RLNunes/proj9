export interface Mandado {
  id: number;
  referenciaDocumental: string;
  registro: string;
  resumo: string;
  pessoasCitadas: string;
  requerente: string;
  palavraChave: string;
  tema: string;
  ano: string;
}

export interface MandadoPage {
  items: Mandado[];
  totalRecords: number;
}
