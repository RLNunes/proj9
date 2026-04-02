import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PalavraChaveComponent } from './palavra-chave.component';
describe('PalavraChaveComponent', () => {
  let component: PalavraChaveComponent;
  let fixture: ComponentFixture<PalavraChaveComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PalavraChaveComponent]
    })
    .compileComponents();
    fixture = TestBed.createComponent(PalavraChaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
