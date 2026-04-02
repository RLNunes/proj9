import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DomainShellComponent } from './domain-shell.component';

describe('DomainShellComponent', () => {
  let component: DomainShellComponent;
  let fixture: ComponentFixture<DomainShellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DomainShellComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DomainShellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
