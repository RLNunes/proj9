import {Component, Input} from '@angular/core';
import {GenericFilterFormComponent} from '../generic-filter-form/generic-filter-form.component';

@Component({
  selector: 'app-entity-page-layout',
  imports: [
    GenericFilterFormComponent
  ],
  templateUrl: './entity-page-layout.component.html',
})
export class EntityPageLayoutComponent {
  @Input() title!: string;
}

