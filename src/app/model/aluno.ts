export class Aluno {
   
    
    constructor(
      public matricula:string,
      public nome:string,
      public senha?:string,
      public id?:number,
      public login?:string
    ) {
      if(!senha)
        this.senha = "default123" // TODO: REMOVER
    }
    

}