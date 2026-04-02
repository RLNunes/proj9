import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicDomainShellComponent } from './public-domain-shell.component';

describe('PublicDomainShellComponent', () => {
  let component: PublicDomainShellComponent;
  let fixture: ComponentFixture<PublicDomainShellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicDomainShellComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PublicDomainShellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
