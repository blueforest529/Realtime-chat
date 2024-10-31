// app.js
const Stomp = require('@stomp/stompjs');
const SockJS = require('sockjs-client');

// 서버에 연결할 WebSocket URL
const socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/public', function (message) {
        console.log('Received message: ' + message.body);
    });

    // 메시지 전송
    stompClient.send("/app/chat.sendMessage", { "Content-Type": "application/json" }, JSON.stringify({
        room: { id: "1" }, // 객체 형태로 전송
        topic: { id: 1 },   // 객체 형태로 전송
        type: "CHAT",
        content: "Hello, WebSocket!",
        sender: "testUser"
    }));
    
});
