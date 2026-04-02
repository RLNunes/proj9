import { Component } from '@angular/core';
import { ApiService } from '../core/services/api.service';
import {ProvocacaoComponent} from '../shared/components/provocacao.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  imports: [
    ProvocacaoComponent
  ]
})
export class HomeComponent {
  apiMessage = '';
  apiError = '';

  constructor(private api: ApiService) {}

  pingApi(): void {
    this.apiMessage = '';
    this.apiError = '';
    this.api.get<unknown>('CircPeticionario/webresources/').subscribe({
      next: (res) => {
        this.apiMessage = 'API respondeu: ' + JSON.stringify(res);
      },
      error: (err) => {
        this.apiError = `Erro ao chamar a API (${err.status}): ${err.message}`;
      },
    });
  }
}

