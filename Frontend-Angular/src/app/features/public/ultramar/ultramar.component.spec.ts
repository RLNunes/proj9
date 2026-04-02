import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UltramarComponent } from './ultramar.component';

describe('UltramarComponent', () => {
  let component: UltramarComponent;
  let fixture: ComponentFixture<UltramarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UltramarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UltramarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

