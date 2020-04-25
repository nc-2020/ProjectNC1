import {Option} from "./option";

export interface Question {
  id: string;
  time: string;
  options: Option[];
  type: {id?: string, name: string};
  text: string;
  quiz_id: string;
  image: string;
}
