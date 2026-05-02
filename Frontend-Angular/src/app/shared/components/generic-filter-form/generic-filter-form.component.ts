import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { SelectModule } from 'primeng/select';
import {
  FilterFieldForm, FilterFieldValue,
  FilterFormControls,
  FilterFormGroup,
  FilterFormValue
} from '../../models';
import {InputText} from 'primeng/inputtext';

@Component({
  selector: 'app-generic-filter-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    SelectModule,
    ButtonModule,
    InputText,
  ],
  templateUrl: './generic-filter-form.component.html',
})
export class GenericFilterFormComponent implements OnChanges {
  @Input() title = 'Filtros';
  @Input() fields: FilterFieldForm[] = [];

  @Output() search = new EventEmitter<FilterFormValue>();
  @Output() clear = new EventEmitter<void>();

  filterForm: FilterFormGroup = new FormGroup<FilterFormControls>({});

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['fields']) {
      this.buildForm();
    }
  }

  handleSearch(): void {
    this.search.emit(this.filterForm.getRawValue());
  }

  handleClear(): void {
    this.filterForm.reset();
    this.clear.emit();
  }

  private buildForm(): void {
    const controls: FilterFormControls = {};

    for (const field of this.fields) {
      controls[field.key] = new FormControl<FilterFieldValue>(null);
    }

    this.filterForm = new FormGroup<FilterFormControls>(controls);
  }
}
