import { ComponentFixture, TestBed } from '@angular/core/testing';
import { OficiosTitulosHomeComponent } from './oficios-titulos-home.component';
describe('OficiosTitulosHomeComponent', () => {
  let component: OficiosTitulosHomeComponent;
  let fixture: ComponentFixture<OficiosTitulosHomeComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OficiosTitulosHomeComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(OficiosTitulosHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
