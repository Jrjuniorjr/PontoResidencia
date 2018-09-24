import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import {Router} from '@angular/router';

import { AuthService } from '../../services/auth/auth.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  info_msg:string
  info_msg_type:string

  // Representa o Form do Template
  private userLoginForm:FormGroup
  // @ViewChild('inputLogin') inputLoginEl:ElementRef // Referência ao #inputLogin do tipo <input>

  constructor(
    private authService:AuthService,  // Serviço de Login, responsável pelo POST da autenticação
    private formBuilder:FormBuilder,   // Builder para as regras & validações do Form
    private router:Router             // Responsável pela navegação caso a autenticação passe
  ) {}

  // Primeira função a ser chamada pelo Angular quando um componente é carregado
  // É preferivel utilizar ngOnInit em vez do constructor() para chamadas de Serviços
  ngOnInit() {
    this.userLoginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password:['', Validators.required]
    })

    // setTimeout(() => this.inputLoginEl.nativeElement.focus(), 100)
    //this.login() // TODO: REMOVER ISSO PQ TÁ AUTO-LOGANDO
  }

  ngOnDestroy() { }

  private login(): void {
    this.authService.login(this.userLogin.toString(), this.userPassword)
      .subscribe( (data:User) => {
        if(data.isEmpty()) {
          this.info_msg = "Erro ao fazer login"
          this.info_msg_type = "info_error"
        } else {
          this.info_msg = "Login realizado com sucesso"
          this.info_msg_type = "info_success"
        }

        setTimeout(() => {
          this.info_msg = ""
          this.info_msg_type = ""
          this.router.navigate([ this.authService.redirectUrl])
          // this.userPassword = ""
        }, 1000)
    })
  }


  /**
   * Chamado pelo botão Submit
   */
  onSubmit() {
    // console.log(this.userLogin)
    // console.log(this.userPassword)

    if(!this.userLogin || !this.userPassword) return
    
    this.login()
    // this.userLoginForm.reset()
  }
  
  // Setters & Getters para o Form
  set userPassword(value:string) { this.userLoginForm.get('password').setValue(value)}
  get userLogin():number { return this.userLoginForm.get('login').value; }

  get userPassword():string { return this.userLoginForm.get('password').value; }


}
