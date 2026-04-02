import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MarcadoresHomeComponent } from './marcadores-home.component';
describe('MarcadoresHomeComponent', () => {
  let component: MarcadoresHomeComponent;
  let fixture: ComponentFixture<MarcadoresHomeComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MarcadoresHomeComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(MarcadoresHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
