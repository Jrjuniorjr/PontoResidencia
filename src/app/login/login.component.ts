import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { LoginService } from '../services/login.service';
import {Router} from '@angular/router';

import { User } from '../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // Representa o Form do Template
  private userLoginForm:FormGroup

  constructor(
    private loginService:LoginService,  // Serviço de Login, responsável pelo POST da autenticação
    private formBuilder:FormBuilder,   // Builder para as regras & validações do Form
    private router:Router             // Responsável pela navegação caso a autenticação passe
  ) {}

  // Primeira função a ser chamada pelo Angular quando um componente é carregado
  // É preferivel utilizar ngOnInit em vez do constructor() para chamadas de Serviços
  ngOnInit() {
    this.userLoginForm = this.formBuilder.group({
      login: ['1', Validators.required],
      password:['1', Validators.required]
    })

    // this.login() // TODO: REMOVER ISSO PQ TÁ AUTO-LOGANDO
  }

  /**
   * Chama o serviço para fazer autenticação (login)
   * O serviço retorna um objeto do tipo usuário
   * {matricula: string, nome:string }
   */
  private login(): void {
    this.loginService.login(this.userLogin.toString(), this.userPassword).subscribe( (data:User) => {
      let matriculaAlunoSize = 10;
      let matriculaAdminSize = 6;

      let returnUrl:string
      let queryParams:User

      if(data.matricula.length === matriculaAlunoSize) {
        returnUrl = '/aluno'
        queryParams = {matricula: data.matricula, nome:data.nome}
      } else if(data.matricula.length === matriculaAdminSize) {
        returnUrl = '/admin'
        queryParams = {matricula: data.matricula, nome:data.nome}
      } else {
        returnUrl = ''
        queryParams = null
      }

      //   this.router.navigate(['/aluno'], { skipLocationChange:true, queryParams: {matricula: data.matricula, nome:data.nome} })
      // else if(data.matricula.length === matriculaAdminSize)
      //   this.router.navigate(['/admin'], { skipLocationChange:true, queryParams: {matricula: data.matricula, nome:data.nome} })

      this.router.navigate([returnUrl], { skipLocationChange:true, queryParams: queryParams })
      this.userPassword = ""
    })
  }


  /**
   * Chamado pelo botão Submit
   */
  onSubmit() {
    this.login()
    // this.userLoginForm.reset()
  }
  
  // Setters & Getters para o Form
  set userPassword(value:string) { this.userLoginForm.get('password').setValue(value)}
  get userLogin():number { return this.userLoginForm.get('login').value; }

  get userPassword():string { return this.userLoginForm.get('password').value; }


}
