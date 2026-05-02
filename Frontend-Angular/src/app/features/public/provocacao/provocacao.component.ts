import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import {
  EntityPageLayoutComponent,
  GenericFilterFormComponent,
  PageTitleComponent,
} from '../../../shared/components';

import { FilterFieldForm, FilterFormValue } from '../../../shared/models';
import { ButtonModule } from 'primeng/button';

import { PublicFilterOptionsService } from '../services';
import {ProvocacaoService} from './services';
import {Provocacao, ProvocacaoSearchFilters} from './models';
import {TableLazyLoadEvent, TableModule} from 'primeng/table';

@Component({
  selector: 'app-provocacao',
  templateUrl: './provocacao.component.html',
  imports: [
    EntityPageLayoutComponent,
    PageTitleComponent,
    GenericFilterFormComponent,
    ButtonModule,
    TableModule
  ]
})
export class ProvocacaoComponent implements OnInit {
  private readonly destroyRef = inject(DestroyRef);
  private readonly publicFilterOptionsService = inject(PublicFilterOptionsService);
  private readonly provocacaoService = inject(ProvocacaoService);

  provocacoes: Provocacao[] = [];
  filterFields: FilterFieldForm[] = [
    {
      type: 'text',
      label: 'Pessoa',
      key: 'pessoa',
    },
    {
      type: 'select',
      label: 'Tema',
      key: 'tema',
      extraOptions: {
        showClear: true
      },
      options: []
    },
    {
      type: 'select',
      label: 'Palavra-Chave',
      key: 'palavraChave',
      extraOptions: {
        showClear: true
      },
      options: []
    }
  ];
  totalRecords = 0;
  currentPage = 0;
  rowsPage = 10;
  loading = false;
  isFiltering = false;
  searchWarningMessage = '';

  ngOnInit(): void {
    this.loadThemeOptions();
    this.loadKeywordOptions();
    this.loadProvocacoes();
  }

  private loadProvocacoes(pageNum = 1, rowsPage = this.rowsPage): void {
    this.loading = true;
    this.isFiltering = false;

    this.provocacaoService
      .getProvocacoes(pageNum, rowsPage)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (page) => {
          this.provocacoes = page.items;
          this.totalRecords = page.totalRecords;
          this.currentPage = pageNum;
          this.rowsPage = rowsPage;
          this.loading = false;
        },
        error: (error) => {
          console.error('Error loading provocacoes', error);
          this.loading = false;
        },
      });
  }

  onPageChange(event: TableLazyLoadEvent): void {
    const rows = event.rows ?? this.rowsPage;
    const first = event.first ?? 0;
    const pageNum = Math.floor(first / rows) + 1;

    this.loadProvocacoes(pageNum, rows);
  }

  onEdit(id: number): void {
    console.log('Edit provocacao:', id);
  }

  onSearch(filters: FilterFormValue): void {
    const pessoa = String(filters['pessoa'] ?? '').trim();
    const tema = Number(filters['tema'] ?? 0);
    const palavraChave = Number(filters['palavraChave'] ?? 0);

    const searchFilters: ProvocacaoSearchFilters = {
      search: 'all',
      pessoa: pessoa || 'all',
      tema,
      palavraChave,
    };

    const hasFilters =
      searchFilters.pessoa !== 'all' ||
      searchFilters.tema !== 0 ||
      searchFilters.palavraChave !== 0;

    if (!hasFilters) {
      this.onClear();
      return;
    }

    this.loading = true;
    this.isFiltering = true;
    this.searchWarningMessage = '';

    this.provocacaoService
      .searchProvocacoes(searchFilters)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (provocacoes) => {
          this.provocacoes = provocacoes;
          this.totalRecords = provocacoes.length;
          this.loading = false;

          if (provocacoes.length > this.rowsPage) {
            this.searchWarningMessage =
              'Esta pesquisa devolveu muitos valores. Por favor, refine a pesquisa para obter menos resultados.';
          }
        },
        error: (error) => {
          console.error('Error searching provocacoes', error);
          this.provocacoes = [];
          this.totalRecords = 0;
          this.loading = false;
        },
      });
  }

  onClear(): void {
    this.isFiltering = false;
    this.searchWarningMessage = '';
    this.currentPage = 1;

    this.loadProvocacoes(1, this.rowsPage);
  }

  private loadThemeOptions(): void {
    this.publicFilterOptionsService
      .getThemeOptions()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (themeOptions) => {
          this.filterFields = this.filterFields.map((field) =>
            field.key === 'tema'
              ? { ...field, options: themeOptions }
              : field,
          );
        },
      });
  }

  private loadKeywordOptions(): void {
    this.publicFilterOptionsService
      .getKeywordOptions()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (keywordOptions) => {
          this.filterFields = this.filterFields.map((field) =>
            field.key === 'palavraChave'
              ? { ...field, options: keywordOptions }
              : field,
          );
        },
      });
  }
}


