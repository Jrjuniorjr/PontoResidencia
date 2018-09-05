import { InMemoryDbService } from 'angular-in-memory-web-api';

export interface IUser {
    id:number 
    matricula:string
    nome:string
    login:string
    password:string
    role:"professor" | "aluno"
}
/*
  // for requests to an `api` base URL that gets heroes from a 'heroes' collection 
  GET api/heroes          // all heroes
  GET api/heroes/42       // the hero with id=42
  GET api/heroes?name=^j  // 'j' is a regex; returns heroes whose name starting with 'j' or 'J'
  GET api/heroes.json/42  // ignores the ".json"
*/
export class Repository implements InMemoryDbService {
  createDb() {
    const users:IUser[] = [ 
        {id:1, matricula:"666", nome: "Professor Random", login:"1234", password:"1234", role:"professor"},
        {id:2, matricula:"123",nome: "Aluno Random", login:"4321", password:"4321", role:"aluno"},
        {id:3, matricula:"999",nome: "Outro Aluno Random", login:"111", password:"111", role:"aluno"},
    
    ]
    
    return {users};
  }
}
