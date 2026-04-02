import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ToponimiaHomeComponent } from './toponimia-home.component';
describe('ToponimiaHomeComponent', () => {
  let component: ToponimiaHomeComponent;
  let fixture: ComponentFixture<ToponimiaHomeComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ToponimiaHomeComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(ToponimiaHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
