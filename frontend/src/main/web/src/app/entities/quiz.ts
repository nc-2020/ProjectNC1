export interface Quiz {
  id: number;
  title: string;
  description: string;
  status?: string;
  user_id: string;
  categories: any;
  image?: string;
}
