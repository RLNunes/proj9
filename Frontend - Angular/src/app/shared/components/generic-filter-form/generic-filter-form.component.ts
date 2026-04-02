import { Component, Input } from '@angular/core';
import { FilterFieldConfig } from '../../models/filter-field-config.model';
@Component({
  selector: 'app-generic-filter-form',
  templateUrl: './generic-filter-form.component.html',
})
export class GenericFilterFormComponent {
  @Input() title = 'Filtros';
  @Input() fields: FilterFieldConfig[] = [];
}
