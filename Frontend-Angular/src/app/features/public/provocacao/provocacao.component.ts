import { Component } from '@angular/core';
import {EntityPageLayoutComponent} from '../../../shared/components/entity-page-layout/entity-page-layout.component';
import {PageTitleComponent} from '../../../shared/components';
import {GenericFilterFormComponent} from '../../../shared/components/generic-filter-form/generic-filter-form.component';
import {FilterFieldForm, FilterFormValue} from '../../../shared/models';
import {Button} from 'primeng/button';
@Component({
  selector: 'app-provocacao',
  templateUrl: './provocacao.component.html',
  imports: [
    EntityPageLayoutComponent,
    PageTitleComponent,
    GenericFilterFormComponent,
    Button
  ]
})
export class ProvocacaoComponent {
  protected filtersFields: FilterFieldForm[] = [
    {
      label: 'Pessoa',
      key: 'pessoaFilter',
      extraOptions: {
        editable: true,
        showClear: true
      },
      options: [
        {label: 'Pessoa 1', value: 'p1'},
        {label: 'Pessoa 2', value: 'p2'},
        {label: 'Pessoa 3', value: 'p3'},
      ],
    },
    {
      label: 'Tema',
      key: 'temaFilter',
      extraOptions: {
        editable: true,
        showClear: true
      },
      options: [
        {label: 'Tema 1', value: 't1'},
        {label: 'Tema 2', value: 't2'},
        {label: 'Tema 3', value: 't3'},
      ]
    },
    {
      label: 'Palavra Chave',
      key: 'palavraChaveFilter',
      extraOptions: {
        editable: true,
        showClear: true
      },
      options: [
        {label: 'Palavra Chave 1', value: 'pc1'},
        {label: 'Palavra Chave 2', value: 'pc2'},
        {label: 'Palavra Chave 3', value: 'pc3'},
      ]
    }
  ];

  protected OnSearch($event: FilterFormValue) {

  }

  protected OnClear() {

  }
}


