import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ComarcaComponent } from './comarca.component';
describe('ComarcaComponent', () => {
  let component: ComarcaComponent;
  let fixture: ComponentFixture<ComarcaComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComarcaComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(ComarcaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
