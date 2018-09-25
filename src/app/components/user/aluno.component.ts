import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';
import { AlunoService } from '../../services/aluno/aluno.service';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { Relatorio } from '../../model/relatorio';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.css']
})
export class AlunoComponent implements OnInit {
  PONTO_ENTRADA = 0
  PONTO_SAIDA = 1

  private user:User
  relatorio:Relatorio

  constructor(
    private alunoService: AlunoService,
    private router:Router, 
    private activatedRoute:ActivatedRoute,
    private auth:AuthService,
  ) { }

  ngOnInit() {
      this.user = this.auth.authUser
      this.updateUserPonto()
  }

  updateUserPonto(r?:Relatorio) {
    if(r) {
      this.relatorio = r
    } else {
      this.alunoService.fetchPonto()
        .subscribe( (data:Relatorio)  => {
          this.relatorio = data
      })
    }
  }


  baterPonto(opcao:number) {
    this.alunoService.baterPonto(opcao)
      .subscribe( (data:Relatorio) => { 
        this.updateUserPonto(data); 
      })
  }
  
  logout() {
    this.auth.logout()
    this.router.navigate([""])
  }
}

