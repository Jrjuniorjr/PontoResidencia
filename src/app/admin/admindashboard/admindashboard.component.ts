import { Component, OnInit } from '@angular/core';
import { Aluno } from '../../model/aluno'
import {Router, ActivatedRoute} from '@angular/router'
import { AdminService } from '../../guards/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../model/user';
import { AuthService } from '../../services/auth.service.';


@Component({
  selector: 'app-admindashboard',
  templateUrl: './admindashboard.component.html',
  styleUrls: ['./admindashboard.component.css']
})
export class AdminDashboardComponent implements OnInit { // TODO: mudar o nome do componente para admin-dashboard, lul

  // Form do componente de inserção de novo aluno
  private novoAlunoForm:FormGroup
  private user:User
  private alunos:Aluno[] = []
  private isCadastroOn:boolean = false;
  // cont:number = 0;
  // selectedUser: Aluno;

  constructor(
    private router:Router, 
    private adminRoute:ActivatedRoute,
    private formBuilder:FormBuilder,
    private auth:AuthService,
    private adminService:AdminService
  ) { }

  ngOnInit() {
    // this.adminRoute.queryParamMap.subscribe( params  => {
    //   this.user = new User(
    //     params.get('matricula'),
    //     params.get('nome')
    //   )

    //   console.log("Bem vindo: " + this.user.nome + " - " + this.user.matricula)
    // })
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

  AbrirTelaRelatorio() {
    this.router.navigate(["/relatorio"]);
  }

  logout() {
    this.user = null
    this.router.navigate([""])
  }

  // TODO: Fazer EDIÇÃO DE ALUNO (Update)
  atualizar(aluno:Aluno) : void {
    this.isCadastroOn = true;
    this.novoAlunoForm.get('matricula').setValue(aluno.matricula)
    this.novoAlunoForm.get('nome').setValue(aluno.nome)
    this.novoAlunoForm.get('password').reset()
  }

  remover(user: Aluno) {
    this.adminService.removerAluno(user.matricula)
      .subscribe( (isStatusOk:boolean) => {
        if(isStatusOk)
          // this.updateAlunoList()
          this.alunos = this.mockRemoveUser(user.matricula) // TODO: Remover mock
        else
          console.log("Erro removendo")
      })
  }

  // inserir(nome:string, matricula:string, senha:string) : void {
  inserir() {
    let aluno = new Aluno(
      this.novoAlunoForm.get('matricula').value,
      this.novoAlunoForm.get('nome').value,
      this.novoAlunoForm.get('password').value
    )

    // Lul, acabei de descobrir que é o .subscribe() que consome o Observable, se não usar nada do observable é utilizado jjjjjjj cu
    this.adminService.inserirAluno(this.user.matricula, aluno)
      .subscribe(data => { 
        console.log("Pos inserir")
        this.alunos.push(aluno) // TODO: remover esta bosta
      })

    // aluno = new Aluno(this.cont++, matricula ,nome , senha, 'x');
    // this.alunos.push(aluno);
  }

  private updateAlunoList() {
    this.adminService.listarAlunos().subscribe(alunos => this.alunos = alunos)
 }
  /* =========== MOCK USERS ===========  */
  // Será que isso vai aparecer no commit history? 

  // mockUsers() {
  //   return [
  //     {matricula:"1000", nome:"Teobaldo Jr"},
  //     {matricula:"2000", nome:"João das Neves"},
  //     {matricula:"3000", nome:"Paty Tricia"},
  //     {matricula:"4000", nome:"Uelintana D'Silva"}
  //   ]
  // }

  // THE MOCKING GOD
  mockRemoveUser(deleted:string) {
    return this.alunos.filter(aluno => aluno.matricula != deleted)
  }

}

