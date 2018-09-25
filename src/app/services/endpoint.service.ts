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
  private EndpointBase = "http://localhost:8080"

  // O endpoint não possui um "contexto" do tipo 'EndPointBase/contexto/API's
  // Se tivesse colocaria aqui também
  
  // SERVICE ENDPOINTS BASE
  private API_PONTO     = "/ponto/" 
  private API_RESIDENTE = "/residente/" 
  private API_RELATORIO = "/relatorio/" 

  // private API_BATER_PONTO_ENTRADA = "/entrada"
  // private API_BATER_PONTO_SAIDA = "/saida"
  // private API_ALUNO = "/aluno"
  // private API_LOGIN = '/residente/login'


  // SERVIÇOS (As funcionalidades do sistema ficam como public)
  // Aqui fica tudo o que o server expõe

  private _api_residente = this.EndpointBase + this.API_RESIDENTE
  private _api_relatorio = this.EndpointBase + this.API_RELATORIO
  private _api_ponto     = this.EndpointBase + this.API_PONTO

  private _login    = "login"
  private _today    = "today"
  private _getone   = "getone"
  private _entrada  = "entrada"
  private _saida    = "saida"

  constructor() { }

   /**
   * POST
   * @Headers - Authorization : {token}
   * @return - HTTP_STATUS_OK | HTTP_STATUS_NOT_FOUND
   * @return -  { matricula:string, nome:string , senha:string, token:string, tipo:string } as Residente
   */
  get login() { return this._api_residente + this._login }

  /**
   * POST
   * @Headers - Authorization : {token}
   * @return - HTTP_STATUS_OK | HTTP_STATUS_CONFLICT
   */
  get inserirAluno() { return this._api_residente }

  get removerAluno() { return this._api_residente }

  get atualizarAluno() { return this._api_residente }

  /**
   * GET
   * @Headers - Authorization : {token}
   * @return  - Array of { matricula:string, nome:string , senha:string, token:string, tipo:string } as Residente[]
   */
  get listarAluno() { return this._api_residente }

  /**
   * GET
   * @Headers - Authorization : {token}
   * @UrlParams - {matricula}
   * @return  - { matricula:string, nome:string , senha:string, token:string, tipo:string } as Residente
   */
  get listarAlunoByMatricula() { return this._api_residente }

  
  /**
   * GET
   * @Headers - Authorization : {token}
   * @return  - { data:string, entrada:string , saida:string, matricula:string } as Relatorio
   */
  get relatorioHoje() { return this._api_relatorio + this._today }

  get relatorioByAluno() { return this._api_relatorio + this._getone }

  get relatorioAll() { return this._api_relatorio }
  get relatorioAllByMatricula() { return this._api_relatorio }

  
  /**
   * POST
   * @Headers - Authorization : {token}
   * @return - HTTP_STATUS_OK | HTTP_STATUS_NOT_FOUND
   * @return  - { data:string, entrada:string , saida:string, matricula:string } as Relatorio
   */
  get pontoEntrada() { return this._api_ponto + this._entrada }
  
  /**
   * POST
   * @Headers - Authorization : {token}
   * @return - HTTP_STATUS_OK | HTTP_STATUS_NOT_FOUND
   * @return  - { data:string, entrada:string , saida:string, matricula:string } as Relatorio
   */
  get pontoSaida() { return this._api_ponto + this._saida }
  
}
