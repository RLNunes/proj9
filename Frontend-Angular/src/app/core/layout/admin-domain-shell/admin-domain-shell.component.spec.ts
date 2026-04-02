import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDomainShellComponent } from './admin-domain-shell.component';

describe('AdminDomainShellComponent', () => {
  let component: AdminDomainShellComponent;
  let fixture: ComponentFixture<AdminDomainShellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminDomainShellComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminDomainShellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
