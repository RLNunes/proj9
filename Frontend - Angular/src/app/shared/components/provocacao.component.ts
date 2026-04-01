import { Component, Input } from '@angular/core';
import {FilterFormComponent} from './filter-form.component';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-provocacao',
  templateUrl: 'provocacao.component.html',
  imports: [
    FilterFormComponent,
    Button
  ],
  standalone: true
})
export class ProvocacaoComponent{
  @Input() title!: string
}
