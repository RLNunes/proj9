import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SecretarioConselheiroHomeComponent } from './secretario-conselheiro-home.component';
describe('SecretarioConselheiroHomeComponent', () => {
  let component: SecretarioConselheiroHomeComponent;
  let fixture: ComponentFixture<SecretarioConselheiroHomeComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SecretarioConselheiroHomeComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(SecretarioConselheiroHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
