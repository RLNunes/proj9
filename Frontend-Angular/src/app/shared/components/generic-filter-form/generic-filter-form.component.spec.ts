import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenericFilterFormComponent } from './generic-filter-form.component';
import {FilterFieldForm} from '../../models';

describe('GenericFilterFormComponent', () => {
  let component: GenericFilterFormComponent;
  let fixture: ComponentFixture<GenericFilterFormComponent>;

  const fields: FilterFieldForm[] = [
    {
      key: 'person',
      label: 'Person',
      type: 'select',
      options: [
        { label: 'Person 1', value: 1 },
        { label: 'Person 2', value: 2 },
      ],
    },
    {
      key: 'theme',
      label: 'Theme',
      type: 'select',
      options: [
        { label: 'Theme 1', value: 10 },
        { label: 'Theme 2', value: 20 },
      ],
    },
  ];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        GenericFilterFormComponent,
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(GenericFilterFormComponent);
    component = fixture.componentInstance;

    fixture.componentRef.setInput('title', 'Filters');
    fixture.componentRef.setInput('fields', fields);

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the title', () => {
    const compiled = fixture.nativeElement as HTMLElement;

    expect(compiled.textContent).toContain('Filters');
  });

  it('should create one form control for each field', () => {
    expect(component.filterForm.contains('person')).toBeTrue();
    expect(component.filterForm.contains('theme')).toBeTrue();
  });

  it('should initialize form controls with null value', () => {
    expect(component.filterForm.getRawValue()).toEqual({
      person: null,
      theme: null,
    });
  });

  it('should emit current form values when searching', () => {
    spyOn(component.search, 'emit');

    component.filterForm.patchValue({
      person: 1,
      theme: 20,
    });

    component.handleSearch();

    expect(component.search.emit).toHaveBeenCalledWith({
      person: 1,
      theme: 20,
    });
  });

  it('should reset form values and emit clear event when clearing', () => {
    spyOn(component.clear, 'emit');

    component.filterForm.patchValue({
      person: 2,
      theme: 10,
    });

    component.handleClear();

    expect(component.filterForm.getRawValue()).toEqual({
      person: null,
      theme: null,
    });
    expect(component.clear.emit).toHaveBeenCalled();
  });
});
