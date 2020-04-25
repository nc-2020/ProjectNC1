import {Option} from "./option";

export interface Question {
  id: number;
  time: number;
  options: Option[];
  type: {id?: string, name: string};
  text: string;
  quiz_id: number;
  image: string;
}
