import { TestBed, async, inject } from '@angular/core/testing';

import { AlunoGuard } from './aluno.guard';

describe('AlunoGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AlunoGuard]
    });
  });

  it('should ...', inject([AlunoGuard], (guard: AlunoGuard) => {
    expect(guard).toBeTruthy();
  }));
});
