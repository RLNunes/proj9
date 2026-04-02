import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaDomainShellComponent } from './consulta-domain-shell.component';

describe('ConsultaDomainShellComponent', () => {
  let component: ConsultaDomainShellComponent;
  let fixture: ComponentFixture<ConsultaDomainShellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultaDomainShellComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultaDomainShellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
