export class User {
    constructor(
        public matricula:string, 
        public nome:string, 
        public isAdmin = false,
        public senha?:string
    ) {}
}