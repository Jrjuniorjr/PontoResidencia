export class Aluno {
   
    
    constructor(
      public matricula:string,
      public nome:string,
      public password?:string,
      public id?:number,
      public login?:string
    ) {
      if(!password)
        this.password = "default123" // TODO: REMOVER
    }
    

}