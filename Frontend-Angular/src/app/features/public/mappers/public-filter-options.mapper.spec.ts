import { mapKeywordApiResponseToFilterOptions, mapThemeApiResponseToFilterOptions } from './public-filter-options.mapper';

describe('public-filter-options.mapper', () => {
  it('should map Tema API responses to filter options', () => {
    expect(
      mapThemeApiResponseToFilterOptions({
        allTemas: [{ pk_tema: '11', tema: 'Tema 11' }],
        rowcount: 1,
      }),
    ).toEqual([{ label: 'Tema 11', value: 11 }]);
  });

  it('should map Palavra-Chave API responses to filter options', () => {
    expect(
      mapKeywordApiResponseToFilterOptions({
        allPalChaves: [{ pk_palavra_chave: '22', palavra_chave: 'Palavra 22' }],
        rowcount: 1,
      }),
    ).toEqual([{ label: 'Palavra 22', value: 22 }]);
  });
});

