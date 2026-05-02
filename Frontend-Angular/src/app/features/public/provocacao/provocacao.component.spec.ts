import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { ProvocacaoComponent } from './provocacao.component';
import { PublicFilterOptionsService } from '../services';

describe('ProvocacaoComponent', () => {
  let component: ProvocacaoComponent;
  let fixture: ComponentFixture<ProvocacaoComponent>;
  let publicFilterOptionsService: jasmine.SpyObj<PublicFilterOptionsService>;

  beforeEach(async () => {
    publicFilterOptionsService = jasmine.createSpyObj<PublicFilterOptionsService>(
      'PublicFilterOptionsService',
      ['getThemeOptions', 'getKeywordOptions'],
    );

    publicFilterOptionsService.getThemeOptions.and.returnValue(
      of([{ label: 'Tema A', value: 10 }]),
    );
    publicFilterOptionsService.getKeywordOptions.and.returnValue(
      of([{ label: 'Palavra A', value: 20 }]),
    );

    await TestBed.configureTestingModule({
      imports: [ProvocacaoComponent],
      providers: [
        {
          provide: PublicFilterOptionsService,
          useValue: publicFilterOptionsService,
        },
      ],
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProvocacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load Tema and Palavra-Chave options into the filter fields', () => {
    const temaField = component.filterFields.find((field) => field.key === 'tema');
    const palavraChaveField = component.filterFields.find((field) => field.key === 'palavraChave');

    expect(temaField?.options).toEqual([{ label: 'Tema A', value: 10 }]);
    expect(palavraChaveField?.options).toEqual([{ label: 'Palavra A', value: 20 }]);
  });
});

