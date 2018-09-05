// export interface IUser {
//     matricula:string
//     nome:string
//     login:string
//     password:string
//     role:"professor" | "aluno"
// }

// export const UserDB:IUser[] = [ 
//     {matricula:"666", nome: "Professor Random", login:"1234", password:"1234", role:"professor"},
//     {matricula:"123",nome: "Aluno Random", login:"4321", password:"4321", role:"aluno"},
//     {matricula:"999",nome: "Outro Aluno Random", login:"111", password:"111", role:"aluno"},

// ]

export class User {
    constructor(public matricula:string, public nome:string) {}
}