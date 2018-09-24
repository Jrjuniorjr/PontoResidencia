import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'

import {Relatorio} from '../../model/relatorio';
import { RelatorioService } from '../../services/relatorio/relatorio.service';

@Component({
  selector: 'app-relatorio',
  templateUrl: './relatorio.component.html',
  styleUrls: ['./relatorio.component.css']
})
export class RelatorioComponent implements OnInit {
  relatorios:Relatorio[] = []

  constructor(
    private router:Router,
    private route:ActivatedRoute,
    private relatorioService:RelatorioService
  ) {}

  ngOnInit() {
    this.route.queryParamMap.subscribe(params => {
      let hasParams = params.get('matricula') || null
      
      this.relatorioService.fetchRelatorio(hasParams)
      .subscribe( (data:Relatorio[]) => {
        console.log(data); 
        this.relatorios = data
      })

    })
  }

  goBack() {
    this.router.navigate(["/admin"])
  }
}
