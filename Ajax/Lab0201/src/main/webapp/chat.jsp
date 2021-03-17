<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JS Asynch</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


</head>

<body>

<script>
    window.onload = function (){
        connect();
        getOnlineUsers();
    }
    var username = "${param.username}";
    var gender = "${param.genderInput}";

    var myObj = new Object();
    myObj.username = username;
    myObj.gender = gender;

    var jsonData = JSON.stringify(myObj);

    var wbSocket = null;
    var wbSocketOnline = null;
    var onlineUsersArr = null;

    var allUser = new Set();

    function connect(){
        wbSocket = new WebSocket("ws://localhost:9191/ajaxchatroom/chatendpoint/"+jsonData);
        wbSocket.onopen = onOpen;
        wbSocket.onmessage = onMessage;
        wbSocket.onclose = redirectToLogin;

    }
    function onMessage(evt){
        var jsonData = evt.data;
        populateMessages(JSON.parse(jsonData));
    }
    function onOpen(){
    }
    function sendMsg() {
        var msgContent = $("#inputMsg").val();
        wbSocket.send(msgContent);
        $("#inputMsg").val("");
    }

    function logout(){
        wbSocket.close();
        wbSocketOnline.close();
    }
    function redirectToLogin(){
        window.location.href = "http://localhost:9191/ajaxchatroom/";
    }

    function connectOnline(){
        wbSocketOnline = new WebSocket("ws://localhost:9191/ajaxchatroom/onlineusers");
        wbSocketOnline.onopen = onOpenOnline;
        wbSocketOnline.onmessage = onMessageOnline;
    }
    function onOpenOnline(){

    }
    function getOnlineUsers(){
        connectOnline();
        setInterval(function(){
            wbSocketOnline.send("");
        },1000);
    }
    function onMessageOnline(evt){
        var onlineUsers = evt.data;
        onlineUsersArr = JSON.parse(onlineUsers);

        populateUsers(onlineUsersArr);
    }

    function populateMessages(messages){

        $("#chatMessages").empty();

        $.each(messages, function(index,val){

            var user = val.sender;
            var imgUrl;
            if(user.gender === "male"){
                imgUrl = "./male.png";
            }else{
                imgUrl = "./female.png";
            }

            var html = "<div class=\"msg\" style=\"margin:5px auto\">\n " +
                "                    <div class=\"row\">\n" +
                "                        <div class=\"col-2\">\n" +
                "                            <img style=\"width: 70px; height: 70px;\" src="+imgUrl+" alt=\"male\">\n" +
                "                        </div>\n" +
                "                        <div class=\"col-10\">\n" +
                "                            <div class=\"row\">"+val.msgContent+"</div>\n" +
                "                            <div class=\"row\">"+user.username+"</div>\n" +
                "                            <div class=\"row form-text\">|"+val.timeStamp+"</div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>";

            $("#chatMessages").append(html);
        });
    }

    function populateUsers(onlineusers){

        $("#usersContainer").empty();

        for(var i = 0 ; i < onlineusers.length ; i++){

            var user = onlineusers[i];
            var imgUrl;
            if(user.gender === "male"){
                imgUrl = "./male.png";
            }else{
                imgUrl = "./female.png";
            }

            var html = "<div class=\"user\" id="+user.id+" style='margin:10px auto;'>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-2\">\n" +
                "                    <img style=\"width: 70px; height: 70px;\" src="+imgUrl+" alt=\"male\">\n" +
                "                </div>\n" +
                "                <div class=\"col-10\">\n" +
                "                    <div class=\"row\">"+user.username+"</div>\n" +
                "                    <div class=\"row form-text\">||Last Active</div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";

            $("#usersContainer").append(html);
        }
    }

</script>


    <div class="container" style="max-width: 750px;">
        <div class="card chatroom" style="margin : 50px auto;">
            <h5 class="card-header">Chat Room!</h5>
            <div class="card-body"  id="chatMessages" style="border: solid #d8d0d0; border-width: 0 0 1px 0;">
            </div>
            <form class="form-inline">
                <div class="row" style="margin: 10px auto; border-top: 1px gray;">
                    <div class="col-md-9">
                        <div class="form-group ">
                            <input type="text" class="form-control" id="inputMsg" placeholder="Your Msg   ">
                        </div>
                    </div>

                    <div class="col-md-3">
                        <button type="button" class="btn btn-primary " onclick="sendMsg()">Send Msg</button>

                    </div>
                </div>
            </form>
        </div>
        <div class="card onlineusers">
            <h5 class="card-header">Online Users</h5>
            <div id="usersContainer" class="card-body" style="border: solid #d8d0d0; border-width: 0 0 1px 0;">

            </div>
            <form class="form-inline">
                <div class="row" style="margin: 10px auto; border-top: 1px gray;">
                    <div class="col-md-9">
                        <div class="form-group ">
                           <h5>Hello there!</h5>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <button type="button" class="btn btn-primary " onclick="logout()">Logout</button>
                    </div>
                </div>
            </form>
        </div>

    </div>

</body>

</html>