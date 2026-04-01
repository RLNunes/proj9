import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-provocacao',
  templateUrl: 'provocacao.component.html',
  standalone: true
})
export class ProvocacaoComponent{
  @Input() title!: string
}
