import { FormControl, FormGroup } from '@angular/forms';

export type FilterFieldType = 'text' | 'select';

export type FilterFieldValue = string | number | null;

export type FilterFormControls = Record<string, FormControl<FilterFieldValue>>;

export type FilterFormGroup = FormGroup<FilterFormControls>;

export type FilterFormValue = Record<string, FilterFieldValue>;

export interface FilterFieldOption {
  label: string;
  value: string | number;
}

export interface FilterFieldForm {
  key: string;
  label: string;
  type: FilterFieldType;
  placeholder?: string;
  options?: FilterFieldOption[];
  extraOptions?: FilterFieldExtraOptions;
}

export interface FilterFieldExtraOptions {
  editable?: boolean;
  showClear?: boolean;
}
