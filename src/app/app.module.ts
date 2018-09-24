import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { RouterModule} from '@angular/router';

// MOCK REST (DEV)
// import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
// import { Repository }  from './repository-mock';

import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AppRoutingModule } from './/app-routing.module';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { RelatorioComponent } from './components/relatorio/relatorio.component';
import { AlunoComponent } from './components/user/aluno.component';
import { TimeoutInterceptor } from './interceptor/timeout-interceptor';
import { TokenInterceptor } from './interceptor/token-interceptor';
import { FormControlComponent } from './components/common/form/form-control/form-control.component';
import { AdminNewUserComponent } from './components/admin/admin-new-user/admin-new-user.component';
import { FocusDirective } from './directives/focus.directive';
import { ResponseInterceptor } from './interceptor/response-interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminDashboardComponent,
    RelatorioComponent,
    AlunoComponent,
    FormControlComponent,
    AdminNewUserComponent,
    FocusDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,

    // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
    // and returns simulated server responses.
    // Remove it when a real server is ready to receive requests.
    // HttpClientInMemoryWebApiModule.forRoot(
    //   Repository, { dataEncapsulation: false }
    // ),

    AppRoutingModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TimeoutInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ResponseInterceptor, multi: true },


  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
