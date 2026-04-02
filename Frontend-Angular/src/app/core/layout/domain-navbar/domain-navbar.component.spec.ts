import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DomainNavbarComponent } from './domain-navbar.component';
describe('DomainNavbarComponent', () => {
  let component: DomainNavbarComponent;
  let fixture: ComponentFixture<DomainNavbarComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DomainNavbarComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(DomainNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
