import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SecretarioConselheiroComponent } from './secretario-conselheiro.component';
describe('SecretarioConselheiroComponent', () => {
  let component: SecretarioConselheiroComponent;
  let fixture: ComponentFixture<SecretarioConselheiroComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SecretarioConselheiroComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(SecretarioConselheiroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
