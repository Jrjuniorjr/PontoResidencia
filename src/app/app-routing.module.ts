import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Components
import { LoginComponent } from './components/login/login.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { RelatorioComponent } from './components/relatorio/relatorio.component';
import { AlunoComponent } from './components/user/aluno.component';
import { AuthGuard } from './guards/auth.guard';
import { AdminGuard } from './guards/admin.guard';
import { AlunoGuard } from './guards/aluno.guard';
import { AdminNewUserComponent } from './components/admin/admin-new-user/admin-new-user.component';


const routes: Routes = [
  { path: 'login',     component: LoginComponent },
  { path: 'admin',     component: AdminDashboardComponent, canActivate:[AuthGuard, AdminGuard] },
  { path: 'relatorio', component: RelatorioComponent ,     canActivate:[AuthGuard, AdminGuard] },
  { path: 'aluno',     component: AlunoComponent ,         canActivate:[AuthGuard, AlunoGuard] },
  { path: 'new-residente', component: AdminNewUserComponent, canActivate:[AuthGuard, AdminGuard] },
  { path: '', redirectTo:'/login', pathMatch:'full' },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
