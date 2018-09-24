import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { AdminService } from '../../../services/admin/admin.service';
import { User, UserRoles } from '../../../model/user';
import { Router, ActivatedRoute } from '@angular/router';

type TInfoType = 'info_warn' | 'info_success' | 'info_error'

@Component({
  selector: 'app-new-user',
  templateUrl: './admin-new-user.component.html',
  styleUrls: ['./admin-new-user.component.css']
})
export class AdminNewUserComponent implements OnInit {
  info_msg:string
  info_msg_type:TInfoType

  userRoleOptions:any[] = [
    {id:0, value:"Residente"},
    {id:1, value:"Admin"},
  ]


  form:FormGroup

  // Métodos para verificar se é um Cadastro ou uma Atualização
  private isCadastro:boolean = true
  public btnMsg = 'Cadastrar' // msg do botão de CADASTRAR / SALVAR

  constructor(
    private formBuilder: FormBuilder, 
    private adminService:AdminService,
    private router:Router,
    private activeRoute:ActivatedRoute
  ){
    this.form = formBuilder.group({
      nome: ['', Validators.required],
      matricula: ['', Validators.required],
      senha: ['', Validators.required],
      tipo: ['', Validators.required],      
    })

    this.activeRoute.queryParams.subscribe(params => {
      if(params['user']) {
        this.isCadastro = false
        this.btnMsg = 'Salvar'

        let user:User = JSON.parse( params['user'] )   

        this.form.setValue({
          nome:user.nome,
          matricula:user.matricula,
          senha:user.senha,
          tipo:user.tipo
        })
      }

    })    
  }

  ngOnInit() {
  }

  onSubmit() {
    if(!this.userMatricula || !this.userNome || !this.userSenha || !this.userTipo) {
      this.setInfoMessage('Preencher todos os dados !', 'info_warn')
      return
    }

    let newUser = new User(
      this.userMatricula.toString(),
      this.userNome,
      "",
      this.userTipo as UserRoles,
      this.userSenha
    )

    if(this.isCadastro)
      this.inserirAluno(newUser)
    else
      this.atualizarAluno(newUser)
  }

  inserirAluno(user:User) {
    this.adminService.inserirAluno(user)
      .subscribe( (data:boolean) => { 
        if(data) {
          let userRole = (this.userTipo === UserRoles.Admin)? 'Admin' : 'Residente'
          this.setInfoMessage(`${userRole} cadastrado com sucesso !`, 'info_success')
        } else {
          this.setInfoMessage('Erro ao cadastrar !',"info_error")
        }

      })
  }

  atualizarAluno(user:User) {
    this.adminService.atualizarAluno(user)
    .subscribe( (data:boolean) => { 
      if(data) {
        let userRole = (this.userTipo === UserRoles.Admin)? 'Admin' : 'Residente'
        this.setInfoMessage(`${userRole} atualizado com sucesso !`, 'info_success')
      } else {
        this.setInfoMessage('Erro ao atualizar !',"info_error")
      }
    })
  }

  goBack() {
    this.router.navigate(["/admin"])
  }

  private setInfoMessage(msg:string, type:TInfoType) {
    this.info_msg = msg
    this.info_msg_type = type

    setTimeout(() => {
      this.info_msg = ''
    }, 3000)
  }

  get userMatricula():number { return this.form.get('matricula').value; }
  get userNome():string { return this.form.get('nome').value; }
  get userSenha():string { return this.form.get('senha').value; }
  get userTipo():string { return this.form.get('tipo').value; }


}
