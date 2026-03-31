import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
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
