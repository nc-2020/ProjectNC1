import { Injectable } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  webSocketEndPoint = 'http://localhost:8080/ws';
  topic = '/topic/get-all/72';
  stompClient: any;
  answers: string;
  constructor() {

  }
  connect() {
    console.log('Initialize WebSocket Connection');
    const ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);

    this.stompClient.connect({}, function(frame) {
      this.stompClient.subscribe(this.topic, function(sdkEvent) {
        this.onMessageReceived(sdkEvent);
      });
      // _this.stompClient.reconnect_delay = 2000;
    }, this.errorCallBack);
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

  /**
   * Send message to sever via web socket
   * @param {*} message
   */
  send(message) {
    console.log('calling logout api via web socket');
    this.stompClient.send('/app/notifications/72', {}, JSON.stringify(message));
  }

  onMessageReceived(message) {
    console.log('Message Recieved from Server :: ' + message);
    this.answers = JSON.stringify(message.body);
  }
}
