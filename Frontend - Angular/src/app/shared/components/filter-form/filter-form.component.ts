import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Select } from 'primeng/select';
import { Button } from 'primeng/button';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { InputText } from 'primeng/inputtext';
@Component({
  selector: 'filter-form',
  templateUrl: 'filter-form.component.html',
  imports: [Select, Button, ReactiveFormsModule, InputText],
})
export class FilterFormComponent {
  @Input() placeholder!: string;
  @Input() optionsPlaceholder!: string;
  @Input() themeOptions!: string[];
  @Input() palavraChaveOptions!: string[];
  @Output() onFilterClick = new EventEmitter();
  filterForm: FormGroup = new FormGroup({});
  handleFilterClick() {
    this.onFilterClick.emit();
  }
  handleClearFilter() {
    // TODO limpar form
  }
}
