import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CapitaniaComponent } from './capitania.component';
describe('CapitaniaComponent', () => {
  let component: CapitaniaComponent;
  let fixture: ComponentFixture<CapitaniaComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CapitaniaComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(CapitaniaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
