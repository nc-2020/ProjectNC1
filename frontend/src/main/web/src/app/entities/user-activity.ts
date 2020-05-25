export interface UserActivity {
  id: number;
  text: string;
  date: string;
  categoryId: number;
  userId: number;
  username:string;
  elem_id?:number;
  elem_name?:string;
  image?:string;
}
