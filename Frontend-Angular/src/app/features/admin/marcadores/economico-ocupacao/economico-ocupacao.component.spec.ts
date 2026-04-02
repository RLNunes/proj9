import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EconomicoOcupacaoComponent } from './economico-ocupacao.component';
describe('EconomicoOcupacaoComponent', () => {
  let component: EconomicoOcupacaoComponent;
  let fixture: ComponentFixture<EconomicoOcupacaoComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EconomicoOcupacaoComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(EconomicoOcupacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
