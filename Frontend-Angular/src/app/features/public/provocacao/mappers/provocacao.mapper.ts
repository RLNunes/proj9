import { Provocacao, ProvocacaoApiItem, ProvocacaoApiResponse } from '../models';

export function mapProvocacaoApiResponseToProvocacoes(
  response: ProvocacaoApiResponse,
): Provocacao[] {
  return response.allProvocacao.map(mapProvocacaoApiItemToProvocacao);
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
