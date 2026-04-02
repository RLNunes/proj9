import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DomainSearchbarComponent } from './domain-searchbar.component';
describe('DomainSearchbarComponent', () => {
  let component: DomainSearchbarComponent;
  let fixture: ComponentFixture<DomainSearchbarComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DomainSearchbarComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(DomainSearchbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
