export enum UserRoles {
    Residente = '0',
    Admin = '1'
}

export class User {
    constructor(
        public matricula:string, 
        public nome:string, 
        public token:string,
        public tipo: UserRoles.Residente | UserRoles.Admin,
        public senha?:string
    ) {}

    public isAdmin(): boolean {
        return this.tipo == UserRoles.Admin
    }

    public isResidente(): boolean {
        return this.tipo == UserRoles.Residente
    }
}