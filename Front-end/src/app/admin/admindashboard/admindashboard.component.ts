import { Component, OnInit } from '@angular/core';
import { Aluno } from 'C:/Users/Lucas/Desktop/Faculdade/Residencia/teste/src/app/aluno'
import {Router, ActivatedRoute} from '@angular/router'

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  constructor(
    private router:Router
  ) { }

  cont:number = 0;
  cadastrarOff:boolean = false;

  users : Aluno[] =  [

  ]

  selectedUser: Aluno;

  ngOnInit() {
  }

  AbrirTelaRelatorio() {
    this.router.navigate(["/relatorio"]);
  }

VoltarTelaLogin() {
  this.router.navigate([""])
}

AbrirTelaCadastro() {
  this.cadastrarOff = true;
}

FecharTelaCadastro() {
  this.cadastrarOff = false;
}

  atualizar(user: Aluno,nome:string,matricula:string,senha:string) : void {
  //update
  }


  remover(user : Aluno) {

    this.users.splice(this.users.indexOf(user),1);
  }

  inserir(nome:string, matricula:string, senha:string) : void {

    let aluno : Aluno;
    aluno = new Aluno(this.cont++, matricula ,nome , senha, 'x');
    this.users.push(aluno);
  }


}

