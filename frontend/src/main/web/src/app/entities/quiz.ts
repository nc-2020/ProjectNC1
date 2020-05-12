export interface Quiz {
  id: number;
  title?: string;
  description: string;
  status?: {id?: number, name?: string};
  favourite?: boolean;
  user_id: string;
  categories?: any;
  image?: string;
}
