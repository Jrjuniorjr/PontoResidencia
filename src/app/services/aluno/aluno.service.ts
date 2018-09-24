import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { EndpointService } from './../endpoint.service';
import { Relatorio } from '../../model/relatorio';
import { BaseService } from '../BaseService';

enum Ponto {
  ENTRADA,
  SAIDA
}

@Injectable({ providedIn: 'root' })
export class AlunoService extends BaseService { 
  PONTO_ENTRADA = 0
  PONTO_SAIDA = 1
  
  constructor(
    http:HttpClient,
    private endpointService:EndpointService,
  ) { 
    super(http)
  }

    baterPonto(opcao:number) : Observable<Relatorio>  {
      let endpoint:string = ''

      if(opcao == Ponto.ENTRADA)
        endpoint = this.endpointService.pontoEntrada
      else if(opcao == Ponto.SAIDA)
        endpoint = this.endpointService.pontoSaida
  
      return this.doPost<Relatorio>(endpoint, {})
        .pipe(
          catchError(err => of(new Relatorio("", ""))) // Se houver algum erro retorna um objeto vazio de relatorio
        )
    }

    public fetchPonto()  {
      return this.doGet<Relatorio>(this.endpointService.relatorioHoje)
        .pipe(
          catchError(err => of(new Relatorio("", "")))
        )
    }
}

