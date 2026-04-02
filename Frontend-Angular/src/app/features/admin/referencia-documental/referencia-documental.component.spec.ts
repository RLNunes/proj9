import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReferenciaDocumentalComponent } from './referencia-documental.component';
describe('ReferenciaDocumentalComponent', () => {
  let component: ReferenciaDocumentalComponent;
  let fixture: ComponentFixture<ReferenciaDocumentalComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReferenciaDocumentalComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(ReferenciaDocumentalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
