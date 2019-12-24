var stompClient1 = null;
var stompClient2 = null;
var stompClient3 = null;



function setConnected1(connected) {
    $("#connect1").prop("disabled", connected);
    $("#disconnect1").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        // $("#conversation").hide();
    }
    $("#greetings").html("");
}

function setConnected2(connected) {
    $("#connect2").prop("disabled", connected);
    $("#disconnect2").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        // $("#conversation").hide();
    }
    $("#greetings").html("");
}

function setConnected3(connected) {
    $("#connect3").prop("disabled", connected);
    $("#disconnect3").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        // $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect1() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient1 = Stomp.over(socket);
    stompClient1.connect({}, function (frame) {
        setConnected1(true);
        console.log('Connected: ' + frame);
        stompClient1.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function connect2() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient2 = Stomp.over(socket);
    stompClient2.connect({}, function (frame) {
        setConnected2(true);
        console.log('Connected: ' + frame);
        // 订阅服务端消息的接口地址，接收订阅消息
        stompClient2.subscribe('/app/subscribeTest', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}
// 接受服务端1对1 消息 展示
function connect3() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient3 = Stomp.over(socket);
    stompClient3.connect({}, function (frame) {
        setConnected3(true);
        console.log('Connected: ' + frame);
        stompClient3.subscribe('/user/queue/message', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient3.subscribe('/user/queue/errors', function (exception) {
            console.log(exception);
            showGreeting(exception);
        });
    });
}

function disconnect1() {
    if (stompClient1 !== null) {
        stompClient1.disconnect();
    }
    setConnected1(false);
    console.log("Disconnected");
}
function disconnect2() {
    if (stompClient2 !== null) {
        stompClient2.disconnect();
    }
    setConnected2(false);
    console.log("Disconnected");
}
function disconnect3() {
    if (stompClient3 !== null) {
        stompClient3.disconnect();
    }
    setConnected3(false);
    console.log("Disconnected");
}

function sendName1() {
    stompClient1.send("/app/hello", {}, JSON.stringify({'name': $("#name1").val()}));
}
function sendName2() {
    stompClient2.send("/app/hello", {}, JSON.stringify({'name': $("#name2").val()}));
}
// 发送1对1消息到服务端
function sendName3() {
    stompClient3.send("/app/message", {}, JSON.stringify({'name': $("#name3").val()}));
}
function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect1" ).click(function() { connect1(); });
    $( "#disconnect1" ).click(function() { disconnect1(); });

    $( "#connect2" ).click(function() { connect2(); });
    $( "#disconnect2" ).click(function() { disconnect2(); });

    $( "#connect3" ).click(function() { connect3(); });
    $( "#disconnect3" ).click(function() { disconnect3(); });
    $( "#send1" ).click(function() { sendName1(); });
    $( "#send2" ).click(function() { sendName2(); });
    $( "#send3" ).click(function() { sendName3(); });
});