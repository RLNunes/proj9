import { ComponentFixture, TestBed } from '@angular/core/testing';
import { OficiosTitulosComponent } from './oficios-titulos.component';
describe('OficiosTitulosComponent', () => {
  let component: OficiosTitulosComponent;
  let fixture: ComponentFixture<OficiosTitulosComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OficiosTitulosComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(OficiosTitulosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
