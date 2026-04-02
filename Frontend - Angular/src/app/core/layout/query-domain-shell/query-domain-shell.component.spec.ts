import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QueryDomainShellComponent } from './query-domain-shell.component';

describe('QueryDomainShellComponent', () => {
  let component: QueryDomainShellComponent;
  let fixture: ComponentFixture<QueryDomainShellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [QueryDomainShellComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QueryDomainShellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
