export interface FilterFieldOption {
  label: string;
  value: string | number | boolean;
}
export interface FilterFieldConfig {
  key: string;
  label: string;
  type?: 'text' | 'select' | 'date' | 'number';
  placeholder?: string;
  options?: FilterFieldOption[];
}
