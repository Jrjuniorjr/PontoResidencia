import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { User } from '../model/user';
import { AlunoService } from '../service/aluno.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.css']
})
export class AlunoComponent implements OnInit {

  user:User
  entrada:string
  saida:string

  constructor(
    private alunoService: AlunoService, 
    private router:ActivatedRoute,
    private location:Location
  ) { }

  ngOnInit() {
    this.router.queryParams.subscribe(params => {
      this.user = new User(params['matricula'], params['nome'], params['role'])

      const {entrada, saida} = this.alunoService.fetchUserPonto(this.user.matricula)
      this.entrada = entrada
      this.saida = saida  
    })
  }

  marcarEntrada() {
    this.entrada = this.alunoService.postUserEntrada(this.user.matricula)
  }

  marcarSaida() {
    this.saida = this.alunoService.postUserSaida(this.user.matricula)
  }

  logout() {
    this.user = null
    this.location.back()
  }
}

