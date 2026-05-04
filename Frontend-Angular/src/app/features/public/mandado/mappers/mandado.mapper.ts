import { Mandado, MandadoApiItem, MandadoApiResponse, MandadoPage } from '../models';

export function mapMandadoApiResponseToMandadoPage(
  response: MandadoApiResponse,
): MandadoPage {
  return {
    items: response.allMandados.map(mapMandadoApiItemToMandado),
    totalRecords: response.rowcount,
  };
}

export function mapMandadoApiItemToMandado(
  item: MandadoApiItem,
): Mandado {
  return {
    id: Number(item.pk_mandado),
    referenciaDocumental: item.referencia_documental,
    registro: item.registro,
    resumo: item.resumo,
    pessoasCitadas: item.pescita,
    requerente: item.pesreq,
    palavraChave: item.palcha,
    tema: item.tema,
    ano: item.ano,
  };
}
