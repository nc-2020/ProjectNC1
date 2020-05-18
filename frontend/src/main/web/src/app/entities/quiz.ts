export interface Quiz {
  id: number;
  title?: string;
  description: string;
  status?: {id?: number, name?: string};
  favorite?: boolean;
  user_id: string;
  categories?: any;
  image?: string;
}
