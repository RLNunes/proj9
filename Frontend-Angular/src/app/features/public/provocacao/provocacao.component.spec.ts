import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvocacaoComponent } from './provocacao.component';

describe('ProvocacaoComponent', () => {
  let component: ProvocacaoComponent;
  let fixture: ComponentFixture<ProvocacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProvocacaoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvocacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

