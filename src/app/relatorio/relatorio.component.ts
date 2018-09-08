import { FormGroup,FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import {Relatorio} from '../Relatorio';
import {Router} from '@angular/router'
import {Aluno} from '../aluno'

enum Meses {
  Janeiro = 1,
  Fevereiro,
  Março,
  Abril,
  Maio,
  Junho,
  Julho,
  Agosto,
  Setembro,
  Outubro,
  Novembro,
  Dezembro
};

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.css']
})

export class RelatorioComponent implements OnInit {

  constructor(public router:Router) {
  
   }

  ngOnInit() {
  }

  meses:string [] = [
      'Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'
  ]

  alunoSelected:Aluno;
  mesSelected:string;

  formRelatorio:FormGroup;

  relatorios : Relatorio[] = [

  {nome:'Glauber',matricula:'1241241',somatorioH:50,entrada:1420,saida:1800},
  {nome:'nas',matricula:'41241',somatorioH:50,entrada:1420,saida:1800},
  {nome:'aegae',matricula:'121421',somatorioH:50,entrada:1420,saida:1800},
  {nome:'braba',matricula:'9129101012',somatorioH:50,entrada:1420,saida:1800},
  {nome:'vava',matricula:'153135',somatorioH:50,entrada:1420,saida:1800}

]

alunos : Aluno[] = [

  {nome:'Glauber',login:'hasuash',matricula:'1241241',id:1,password:'x'},
]

filtroAluno(event) {

  console.log(this.alunoSelected.nome);
  //filtroRelatoriosPorNome(nome)

}

filtroMes(event) {

  console.log(this.mesSelected);
  //filtroRelatoriosPorMes(mes)
  
  }

voltarTelaAdmin() {

  this.router.navigate(["/admin"])

}



}
