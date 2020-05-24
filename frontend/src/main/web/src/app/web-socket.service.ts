import { Injectable } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {UserService} from "./services/user.service";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  webSocketEndPoint = 'http://localhost:8080/socket';
  topic = '/message';
  stompClient: any;
  answers: string;
  constructor(private userService: UserService) {
    this.connect();
  }
  connect() {

    this.stompClient = Stomp.Stomp.over(function() {
          return new SockJS(this.webSocketEndPoint);
        });
    this.stompClient.connect({
        "Authorization" : "Bearer_"+this.userService.getToken()
      }, function(frame) {
      console.log('Connected: ' + frame);
      this.stompClient.subscribe(this.topic, function(messageOutput) {
        console.log(JSON.parse(messageOutput.body));
      });
    });

  }

  disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected');
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error) {
    console.log('errorCallBack -> ' + error);
    setTimeout(() => {
      this.connect();
    }, 5000);
  }


  send(message) {
    console.log('calling logout api via web socket');
    this.stompClient.send('/hello', {}, JSON.stringify(message));
  }

  onMessageReceived(message) {
    this.answers = JSON.stringify(message.body);
    console.log('Message Recieved from Server :: ' + this.answers);

  }
}
