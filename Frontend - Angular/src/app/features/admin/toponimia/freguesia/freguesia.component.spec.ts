import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FreguesiaComponent } from './freguesia.component';
describe('FreguesiaComponent', () => {
  let component: FreguesiaComponent;
  let fixture: ComponentFixture<FreguesiaComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FreguesiaComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(FreguesiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
