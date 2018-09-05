import { Component, OnInit } from '@angular/core';
import {Relatorio} from 'C:/Users/Lucas/Desktop/Faculdade/Residencia/teste/src/app/Relatorio';
import {Router} from '@angular/router'

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.css']
})
export class RelatorioComponent implements OnInit {

  constructor(public router:Router) { }

  ngOnInit() {
  }

relatorios : Relatorio[] = [

  {nome:'Glauber',matricula:'1241241',somatorioH:50,entrada:1420,saida:1800},
  {nome:'nas',matricula:'41241',somatorioH:50,entrada:1420,saida:1800},
  {nome:'aegae',matricula:'121421',somatorioH:50,entrada:1420,saida:1800},
  {nome:'braba',matricula:'9129101012',somatorioH:50,entrada:1420,saida:1800},
  {nome:'vava',matricula:'153135',somatorioH:50,entrada:1420,saida:1800}

]

voltarTelaAdmin() {

  this.router.navigate(["/admin"])

}

}
