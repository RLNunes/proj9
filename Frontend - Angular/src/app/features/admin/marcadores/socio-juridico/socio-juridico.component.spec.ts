import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SocioJuridicoComponent } from './socio-juridico.component';
describe('SocioJuridicoComponent', () => {
  let component: SocioJuridicoComponent;
  let fixture: ComponentFixture<SocioJuridicoComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SocioJuridicoComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(SocioJuridicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
