import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';
import { AlunoService } from '../../services/aluno/aluno.service';
import { Location } from '@angular/common';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { Relatorio } from '../../model/relatorio';

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
    private router:Router, 
    private activatedRoute:ActivatedRoute,
    private auth:AuthService,
    private location:Location
  ) { }

  ngOnInit() {
    // this.activatedRoute.queryParams.subscribe(params => {
    //   this.user = new User(params['matricula'], params['nome'])
    //   this.updateUserPonto()

    //   // localStorage.clear()
    // })

      this.user = this.auth.authUser
      // this.alunoService.clearStorage(this.user.matricula+"-entrada")
      this.updateUserPonto()
      

  }

  updateUserPonto(r?:Relatorio) {
    if(r) {
      this.entrada = r.entrada
      this.saida = r.saida
    } else {
      this.alunoService.getPontoHoje().subscribe( (data:Relatorio)  => {
        this.entrada = data.entrada
        this.saida = data.saida
      })
    }
  }

  baterPonto(opcao:string) {
    // console.log('baterPontoEntrada aluno.component')
    this.alunoService.baterPontoEntrada(this.user.matricula,opcao)
    .subscribe(data => { 
      this.updateUserPonto(data); 
    })
  }

  
  // baterPontoSaida() {
  //   this.alunoService.baterPonto(this.user.matricula)
  //   this.updateUserPonto()
  // }

  logout() {
    this.auth.logout()
    this.router.navigate([""])
    // this.location.back()
  }

  get USER_ENTRADA() { return this.user.matricula + "-entrada"}
  get USER_SAIDA() { return this.user.matricula + "-saida"}

}

