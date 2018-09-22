export class User {
    constructor(
        public matricula:string, 
        public nome:string, 
        public token:string,
        public adm: '0' | '1', // 0 aluno - 1 admin
        public senha?:string
    ) {}
}