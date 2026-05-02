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

@Component({
  selector: 'app-provocacao',
  templateUrl: './provocacao.component.html',
  imports: [
    EntityPageLayoutComponent,
    PageTitleComponent,
    GenericFilterFormComponent,
    ButtonModule
  ]
})
export class ProvocacaoComponent implements OnInit {
  private readonly destroyRef = inject(DestroyRef);
  private readonly publicFilterOptionsService = inject(PublicFilterOptionsService);

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
        editable: true,
        showClear: true
      },
      options: []
    },
    {
      type: 'select',
      label: 'Palavra-Chave',
      key: 'palavraChave',
      extraOptions: {
        editable: true,
        showClear: true
      },
      options: []
    }
  ];

  ngOnInit(): void {
    this.loadThemeOptions();
    this.loadKeywordOptions();
  }

  onSearch(filters: FilterFormValue): void {
    console.log('Provocação filters:', filters);
  }

  onClear(): void {
    console.log('Provocação filters cleared');
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


