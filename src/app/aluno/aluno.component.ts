import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { User } from '../model/user';
import { AlunoService } from '../services/aluno.service';
import { Location } from '@angular/common';
import { AuthService } from '../services/auth.service.';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.css']
})
export class AlunoComponent implements OnInit {

  private user:User
  private entrada:string
  private saida:string

  constructor(
    private alunoService: AlunoService, 
    private router:ActivatedRoute,
    private auth:AuthService,
    private location:Location
  ) { }

  ngOnInit() {
    // this.router.queryParams.subscribe(params => {
    //   this.user = new User(params['matricula'], params['nome'])
    //   this.updateUserPonto()

    //   // localStorage.clear()
    // })

      this.user = this.auth.authUser
      this.updateUserPonto()

  }

  updateUserPonto() {
    this.entrada = this.alunoService.getPonto(this.USER_ENTRADA)
    this.saida = this.alunoService.getPonto(this.USER_SAIDA)
  }

  baterPonto() {
    this.alunoService.baterPonto(this.user.matricula)
    this.updateUserPonto()
  }

  logout() {
    this.user = null
    this.location.back()
  }

  get USER_ENTRADA() { return this.user.matricula + "-entrada"}
  get USER_SAIDA() { return this.user.matricula + "-saida"}

}

