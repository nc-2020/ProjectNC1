export interface Quiz {
  id: number;
  title: string;
  description: string;
  status?: string;
  favourite?: boolean;
  user_id: string;
  categories: any;
  image?: string;
}
