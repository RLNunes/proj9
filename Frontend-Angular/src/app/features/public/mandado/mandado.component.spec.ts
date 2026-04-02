import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MandadoComponent } from './mandado.component';

describe('MandadoComponent', () => {
  let component: MandadoComponent;
  let fixture: ComponentFixture<MandadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MandadoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MandadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

