import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Select} from 'primeng/select';
import {Button} from 'primeng/button';


@Component({
  selector: 'filter-form',
  templateUrl: 'filter-form.component.html',
  imports: [
    Select,
    Button
  ],
  standalone: true
})
export class FilterFormComponent{
  @Input() temaOptions!: string[]
  @Input() palavraChaveOptions!: string[]

  @Output() onFilterClick = new EventEmitter()

  handleFilterClick(){
    this.onFilterClick.emit()
  }

  handleClearFilter(){
   //TODO limpar form
  }
}
