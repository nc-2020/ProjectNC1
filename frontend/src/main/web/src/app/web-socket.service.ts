import { Injectable } from '@angular/core';
import {UserService} from "./services/user.service";
declare var SockJS;
declare var Stomp;
@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  constructor(private userService: UserService) {

  }
  public stompClient;
  public msg = [];
  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8080/ws';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect({"Authorization" : "Bearer_"+this.userService.getToken()
      }, function(frame) {
      that.stompClient.subscribe('/topic', (message) => {
        if (message.body) {
          that.msg.push(message.body);
        }
      });
    });
  }

  getNotifications(userId) {
    this.stompClient.send('/app/get/topic' , {}, userId);
  }
}



// import { Injectable } from '@angular/core';
// import * as Stomp from '@stomp/stompjs';
// import * as SockJS from 'sockjs-client';
// import {UserService} from "./services/user.service";
//
// @Injectable({
//   providedIn: 'root'
// })
// export class WebSocketService {
//   webSocketEndPoint = 'http://localhost:8080/ws';
//   topic = '/topic/getall';
//   stompClient: any;
//   answers: string;
//   constructor(private userService: UserService) {
//
//   }
//   connect() {
//
//     this.stompClient = Stomp.Stomp.over(function() {
//           return new SockJS(this.webSocketEndPoint);
//         });
//     this.stompClient.connect({
//         "Authorization" : "Bearer_"+this.userService.getToken()
//       }, function(frame) {
//       console.log('Connected: ' + frame);
//       this.stompClient.subscribe('/topic/getall', function(messageOutput) {
//         console.log(JSON.parse(messageOutput.body));
//       });
//     });
//
//   }
//
//   disconnect() {
//     if (this.stompClient !== null) {
//       this.stompClient.disconnect();
//     }
//     console.log('Disconnected');
//   }
//
//   // on error, schedule a reconnection attempt
//   errorCallBack(error) {
//     console.log('errorCallBack -> ' + error);
//     setTimeout(() => {
//       this.connect();
//     }, 5000);
//   }
//
//
//   send(message) {
//     console.log('calling logout api via web socket');
//     this.stompClient.send('/app/notifications', {}, JSON.stringify(message));
//   }
//
//   onMessageReceived(message) {
//     this.answers = JSON.stringify(message.body);
//     console.log('Message Recieved from Server :: ' + this.answers);
//
//   }
// }
