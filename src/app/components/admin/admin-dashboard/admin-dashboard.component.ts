import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AdminService } from '../../../services/admin/admin.service';
import { AuthService } from '../../../services/auth/auth.service';
import { User, UserRoles } from '../../../model/user';


@Component({
  selector: 'app-admindashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit { 

  private novoAlunoForm:FormGroup // Form do componente de inserção de novo aluno
  private user:User
  private alunos:User[] = []
  private isCadastroOn:boolean = false;

  constructor(
    private router:Router, 
    private formBuilder:FormBuilder,
    private auth:AuthService,
    private adminService:AdminService
  ) { }

  ngOnInit() {
    this.user = this.auth.authUser 
    console.log("Bem vindo: " + this.user.nome + " - " + this.user.matricula)


    this.novoAlunoForm = this.formBuilder.group({
      matricula:  ['', Validators.required],
      nome:       ['', Validators.required],
      password:   ['', Validators.required]
    })

    // Primeiro listar ao entrar na página de Admin
    this.updateAlunoList()
  }

  onCadastrar() {
    this.isCadastroOn = !this.isCadastroOn;
    this.novoAlunoForm.reset();
    this.router.navigate(['/new-residente'])
  }

  AbrirTelaRelatorio(user:User) {
    this.router.navigate(["/relatorio"], { queryParams: {matricula: user? user.matricula : ""}});
  }

  logout() {
    this.auth.logout()
    this.router.navigate([""])
  }

  atualizar(user:User) : void {
    this.router.navigate(['/new-residente'], {queryParams: { "user" : JSON.stringify(user) } })
  }

  remover(user: User) {
    this.adminService.removerAluno(user.matricula)
      .subscribe( data => {
        this.updateAlunoList()
      })
  }

  private updateAlunoList() {
    this.adminService.listarAlunos().subscribe(users => this.alunos = users)
 }

}

