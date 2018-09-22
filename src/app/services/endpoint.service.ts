/*
 * EndpointService.ts
 * Repositorio de constantes para o Endpoint 
 * 
 * Server Endpoint
 */

import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' }) 
export class EndpointService {
  // BASE
  public EndpointBase = "http://localhost:8080"
  
  // SERVICE ENDPOINTS BASE
  public API_LOGIN_ALUNO = "/aluno/" 
  public API_LOGIN_PROFESSOR = "/professor/" 
  private API_BATER_PONTO = "/baterPonto" // "serviceAluno/aluno/baterPonto/{matricula}"
  private API_BATER_PONTO_ENTRADA = "/entrada"
  private API_BATER_PONTO_SAIDA = "/saida"
  private API_ALUNO = "/aluno"
  private API_RELATORIO = '/relatorio'
  private API_LOGIN = '/residente/login'


  // SERVIÇOS
  private SERVICE_LISTAR_ALUNO = "/alunos"
  private SERVICE_ALUNO = "/aluno/"
  // private SERVICE_REMOVER_ALUNO = "/aluno/"
  // private SERVICE_INSERIR_ALUNO = "/aluno/"

  constructor() { }

  get login() { return this.EndpointBase + this.API_LOGIN }
  //get ponto() { return this.EndpointBase + this.API_ALUNO + "/aluno" + this.API_BATER_PONTO }
  get EndpointAluno() { return this.EndpointBase + this.API_ALUNO }
  get EndpointRelatorio() { return this.EndpointBase + this.API_RELATORIO }


  /**
   * Listar Aluno Endpoint
   * { JSON }
   * @return  - { matricula:string, nome:string , senha:number } as Aluno
   */
  get listarAluno() { return this.EndpointAluno }

  /**
   * Endpoint padrão que varia baseado no HTTP OPERATION + URL PARAMS
   * POST /{matriculaProf} -> Req.Body: Aluno   => Create
   * POST /baterPonto/{matriculaAluno}          => SetPonto
   * DELETE /{matriculaAluno}                   => Delete
   * GET /{matriculaAluno}                      => Read
   * PUT /{Aluno} -> Req.Body: Aluno            => Update
   */
  get serviceAluno() { return this.EndpointAluno + this.SERVICE_ALUNO }

  /**
   * DELETE /{matriculaAluno} 
   */
  get removerAluno() { return this.EndpointAluno }

   /**
    * POST /{matriculaProf} -> Req.Body: Aluno()   => Create
    */
  get inserirAluno() { return this.EndpointAluno }

   /**
    * PUT /{matriculaProf} -> Req.Body: Aluno()   => Update
    * //TODO: back end esqueceu do matriculaProf
    */
   get updateAluno() { return this.EndpointAluno }
  

   get baterPontoEntrada() {return this.EndpointRelatorio + this.API_BATER_PONTO_ENTRADA}

   get baterPontoSaida() {return this.EndpointRelatorio + this.API_BATER_PONTO_SAIDA}

}
