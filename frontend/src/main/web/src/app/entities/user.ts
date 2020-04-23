export interface User {
  id: string;
  firstname: string;
  lastname: string;
  username: string;
  password?: string;
  email: string;
  role: {id?: string, name: string};
  image?: string;

}
