import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AdminService } from '../../../services/admin/admin.service';
import { AuthService } from '../../../services/auth/auth.service';
// import { User } from '../../../model/aluno'
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
  private inserirMode = "cadastro"

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

  // TODO: Fazer EDIÇÃO DE ALUNO (Update)
  atualizar(user:User) : void {
    this.router.navigate(['/new-residente'], {queryParams: { "user" : JSON.stringify(user) } })
  }

  remover(user: User) {
    this.adminService.removerAluno(user.matricula)
      .subscribe( data => {
        this.updateAlunoList()
      })
    // alert("Não Implementado !")
  }

  // inserir() {
  //   let user = new User(
  //     this.novoAlunoForm.get('matricula').value,
  //     this.novoAlunoForm.get('nome').value,
  //     "",
  //     UserRoles.Residente,
  //     this.novoAlunoForm.get('password').value
  //   )

  //   if(this.inserirMode === 'cadastro') {
  //     // Lul, acabei de descobrir que é o .subscribe() que consome o Observable, se não usar nada do observable é utilizado jjjjjjj cu
  //     this.adminService.inserirAluno(user)
  //       .subscribe(data => { 
  //         // this.updateAlunoList()
  //         // this.alunos.push(aluno) // TODO: remover esta bosta
  //       })
  //   } else {
  //     this.adminService.updateAluno(this.user.matricula, user)
  //       .subscribe(data => { 
  //         this.updateAlunoList()
  //         // this.alunos.push(aluno) // TODO: remover esta bosta
  //         this.inserirMode = "cadastro"
  //       })
  //   }
  //   // aluno = new User(this.cont++, matricula ,nome , senha, 'x');
  //   // this.alunos.push(aluno);
  // }

  private updateAlunoList() {
    this.adminService.listarAlunos().subscribe(users => this.alunos = users)
 }

  // THE MOCKING GOD
  removerAluno(deleted:string) {
    // this.adminService.removerAluno(deleted).subscribe(resposta => console.log(resposta))
    return this.alunos.filter(aluno => aluno.matricula != deleted)
  }

}

