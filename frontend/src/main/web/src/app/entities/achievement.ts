export interface Achievement {
  id: number;
  title: string;
  amountOfQuizzes: number;
  amountOfCreated: number;
  icon?: any;
  creatorUserId?: number;
  categoryId: number;
}
