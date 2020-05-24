export interface Session {
  id?: number;
  quiz_id: number;
  accessCode: any;
  date: string;
  status?: {id?: string, name: string};
}
