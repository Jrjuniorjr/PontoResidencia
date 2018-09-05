import { Component, OnInit } from '@angular/core';
import { Aluno } from 'C:/Users/Lucas/Desktop/Faculdade/Residencia/teste/src/app/aluno'
import {Router} from '@angular/router'

@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  constructor(
    private router:Router
  ) { }

  cont:number = 3;
  cadastrarOff:boolean = false;

  users : Aluno[] =  [

    {id:1, matricula:"666", nome: "Professor Random", login:"1234", password:"1234"},
    {id:2, matricula:"123",nome: "Aluno Random", login:"4321", password:"4321"},
    {id:3, matricula:"999",nome: "Outro Aluno Random", login:"111", password:"111"},
  ]

  ngOnInit() {
  }

  abrirTelaRelatorio() {


  }

AbrirTelaCadastro() {
  this.cadastrarOff = true;
}

FecharTelaCadastro() {
  this.cadastrarOff = false;
}

  inserir(nome:string, matricula:string, senha:string) : void {

    let aluno : Aluno;
    aluno = new Aluno(this.cont++, matricula ,nome , senha, 'x');
    this.users.push(aluno);
  }

}
