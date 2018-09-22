import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Components
import { LoginComponent } from './login/login.component';
import { AdminDashboardComponent } from './admin/admindashboard/admindashboard.component';
import { RelatorioComponent } from './relatorio/relatorio.component';
import { AlunoComponent } from './aluno/aluno.component';
import { AuthGuard } from './guards/auth.guard';
import { AdminGuard } from './guards/admin.guard';
import { AlunoGuard } from './guards/aluno.guard';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo:'login', pathMatch:'full' },
  { path: 'admin', component: AdminDashboardComponent,canActivate:[AuthGuard, AdminGuard] },
  { path: 'relatorio', component: RelatorioComponent , canActivate:[AuthGuard, AdminGuard]},
  { path: 'aluno', component: AlunoComponent , canActivate:[AuthGuard, AlunoGuard]}  

];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
