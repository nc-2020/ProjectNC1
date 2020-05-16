export interface Session {
  id?: number;
  quizId: number;
  accessCode: any;
  date: string;
  status?: {id?: string, name: string};
}
