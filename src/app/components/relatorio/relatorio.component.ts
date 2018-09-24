import { ActivatedRoute } from '@angular/router';
import { FormGroup,FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import {Relatorio} from '../../model/relatorio';
import {Router} from '@angular/router'
import {Aluno} from '../../model/aluno'
import { RelatorioService } from '../../services/relatorio/relatorio.service';

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
  alunoSelected:Aluno;
  mesSelected:string;
  formRelatorio:FormGroup;
  private relatorios:Relatorio[] = []

  // relatorios : Relatorio[] = [
  //   {nome:'Glauber',matricula:'1241241',somatorioH:50,entrada:1420,saida:1800},
  //   {nome:'nas',matricula:'41241',somatorioH:50,entrada:1420,saida:1800},
  //   {nome:'aegae',matricula:'121421',somatorioH:50,entrada:1420,saida:1800},
  //   {nome:'braba',matricula:'9129101012',somatorioH:50,entrada:1420,saida:1800},
  //   {nome:'vava',matricula:'153135',somatorioH:50,entrada:1420,saida:1800}
  // ]

//   [
//     { "matricula":"1234567890", "nome":"Pitt, The Star Lord", "entrada":"Hora de Entrada", "saida":"Hora de Saida"},
//     { "matricula":"0987654321", "nome":"Gl400b3r", "entrada":"Hora de Entrada", "saida":"Hora de Saida"},
//     { "matricula":"6666666666", "nome":"Shido", "entrada":"Hora de Entrada", "saida":"Hora de Saida"},
//     { "matricula":"0000000000", "nome":"Pedro, THENEZORFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", "entrada":"Hora de Entrada", "saida":"Hora de Saida"},
//     { "matricula":"1357924680", "nome":"BaBsSsSSsxXx", "entrada":"Hora de Entrada", "saida":"Hora de Saida"},
//     { "matricula":"1020304050", "nome":"DJ0000000000000000000LHA", "entrada":"Hora de Entrada", "saida":"Hora de Saida"}
// ]


  // alunos : Aluno[] = [
  //   {nome:'Glauber',login:'hasuash',matricula:'1241241',id:1,password:'x'},
  // ]
  matricula:string
  constructor(
    private router:Router,
    private route:ActivatedRoute,
    private relatorioService:RelatorioService
  ) {}

  ngOnInit() {
    this.route.queryParamMap.subscribe(params => {
      // let matricula = params['matricula']?
      let gambiarra = params.get('matricula') || ""
      console.log(params)

      this.relatorioService.fetchRelatorio(gambiarra).subscribe( (data:Relatorio[]) => {console.log(data); this.relatorios = data})

    })
    // this.relatorioService.fetchRelatorio().subscribe( (data:Relatorio[]) => this.relatorios = data)
  }

  // meses:string [] = [
  //     'Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'
  // ]

  

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
