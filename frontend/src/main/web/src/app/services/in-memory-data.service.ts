import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import {Quiz} from "../entities/quiz";
import {Question} from "../entities/question";


@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const quizes: Quiz[] = [
      { id: 1, title: 'Java', description: '', status: '', userId: 1 },
      { id: 2, title: 'Angular', description: '', status: '', userId: 1 },
      { id: 3, title: 'SQL', description: '', status: '', userId: 2 },
      { id: 4, title: 'Spring', description: '', status: '', userId: 2 },
      { id: 5, title: 'JDBC', description: '', status: '', userId: 4 },
    ];

    const questions: Question[] = [
      { id: 1,
        time: 30,
        options: [
          { id: 1,
            correct: true,
            text: 'Programming language'
          }],
        type: {
          id: '1',
          name: 'Select option'
        },
        text: 'What is Java',
        quiz_id: 1,
        image: ''
      },
      { id: 2,
        time: 30,
        options: [
          { id: 1,
            correct: true,
            text: 'Programming language'
          }],
        type: {
          id: '1',
          name: 'Select option'
        },
        text: 'What is Angular',
        quiz_id: 2,
        image: ''
      },
      { id: 3,
        time: 30,
        options: [
          { id: 1,
            correct: true,
            text: 'Programming language'
          }],
        type: {
          id: '1',
          name: 'Select option'
        },
        text: 'What is Sql',
        quiz_id: 3,
        image: ''
      },
    ];
    return {quizes, questions};
  }

  // Overrides the genId method to ensure that a hero always has an id.
  // If the heroes array is empty,
  // the method below returns the initial number (11).
  // if the heroes array is not empty, the method below returns the highest
  // hero id + 1.
  genId(quizes: Quiz[]): number {
    return quizes.length > 0 ? Math.max(...quizes.map(quiz => quiz.id)) + 1 : 1;
  }
}
