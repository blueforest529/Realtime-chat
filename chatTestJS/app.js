const Stomp = require('@stomp/stompjs');
const SockJS = require('sockjs-client');

// WebSocket 연결을 위한 SockJS 팩토리 설정
const socketFactory = () => new SockJS('http://localhost:8080/chat');
const stompClient = Stomp.Stomp.over(socketFactory);

// 재연결 설정
stompClient.reconnectDelay = 5000;


stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);

    // 서버로부터 수신할 메시지 경로 구독
    stompClient.subscribe('/topic/messages', function (message) {
        console.log('Received message: ' + message.body);
    });

    // 5초에 한 번씩 메시지 전송
    setInterval(() => {
        stompClient.send("/app/sendMessage", { "Content-Type": "application/json" }, JSON.stringify({
            sender: "testUser",
            content: "Hello, WebSocket!",
            timestamp: Date.now(),
            topic: "general"
        }));
        console.log('Message sent at:', new Date().toLocaleTimeString());
    }, 5000); // 5000ms = 5초
});
