import { Provocacao, ProvocacaoApiItem, ProvocacaoApiResponse, ProvocacaoPage } from '../models';

export function mapProvocacaoApiResponseToProvocacaoPage(
  response: ProvocacaoApiResponse,
): ProvocacaoPage {
  return {
    items: response.allProvocacao.map(mapProvocacaoApiItemToProvocacao),
    totalRecords: response.rowcount,
  };
}

export function mapProvocacaoApiItemToProvocacao(
  item: ProvocacaoApiItem,
): Provocacao {
  return {
    id: Number(item.pk_provocacao),
    destinatarioAutoridadeTratamento: item.destinatario_autoridade_tratamento,
    resumo: item.resumo,
    referenciaDocumental: item.referencia_documental,
    pessoasCitadas: item.pescita,
    requerente: item.pesreq,
    palavraChave: item.palcha,
    tema: item.tema,
    ano: item.ano,
  };
}
