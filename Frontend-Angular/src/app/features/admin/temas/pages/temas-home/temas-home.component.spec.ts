import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TemasHomeComponent } from './temas-home.component';
describe('TemasHomeComponent', () => {
  let component: TemasHomeComponent;
  let fixture: ComponentFixture<TemasHomeComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TemasHomeComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(TemasHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
