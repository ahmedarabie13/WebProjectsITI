<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<script>
    $(document).ready(function () {
        setInterval(handler,1000);
        function handler(){
            $.get ("MyServletUrl",ajaxCallBack);


        }
        function ajaxCallBack (responseTxt, statusTxt, xhr){
            if (statusTxt == "success"){
                let tabelBody = $("#data");
                tabelBody.empty();
                var messages= JSON.parse(responseTxt);
                $.each(messages, function (index, val) {
                    messageRow = "<tr>" + "<td>" + val.id + "</td>" + "<td>" + val.name + "<td>" + val.message + "</td>" + "</tr>";
                    tabelBody.append(messageRow);
                });
            }
        }
            // $.ajax({
            //     url: 'MyServletUrl',
            //     type: 'GET',
            //     dataType: 'json',
            //     success: function (data) {
            //         let tabelBody = $("#data");
            //         tabelBody.empty();
            //
            //         $.each(data, function (index, val) {
            //             messageRow = "<tr>" + "<td>" + val.id + "</td>" + "<td>" + val.name + "<td>" + val.message + "</td>" + "</tr>";
            //             tabelBody.append(messageRow);
            //         })
            //     }
            // }),1000);
        $("#sendBtn").click(function () {
            var name = $("#user").val();
            var message = $("#message").val();
            var jsonData = {"name": name, "message": message};
            $.ajax({
                url: 'MyServletUrl',
                type: 'POST',
                // contentType: 'application/json',
                data: jsonData,
                dataType: 'json',
                success: function (data) {
                    let tabelBody = $("#data");
                    tabelBody.empty();

                    $.each(data, function (index, val) {
                        messageRow = "<tr>" + "<td>" + val.id + "</td>" + "<td>" + val.name + "<td>" + val.message + "</td>" + "</tr>";
                        tabelBody.append(messageRow);
                    });

                }
            });

        });
    });



</script>
<div class="container">
    <div class="row">
        <div class="col-sm-12 text-primary" align="center">
            <h1>Chat Room App</h1>
        </div>
    </div>
    <div class="row col-sm-12">
        <div class="form-group">
            <label for="user">Name:</label>
            <input type="text" class="form-control col-sm-4" id="user">
        </div>
        <br>
        <br>
        <div class="form-group">
            <label for="message">Message:</label>
            <input type="text" class="form-control col-sm-4" id="message">
        </div>
        <br>
        <br>
        <button type="button" class="btn btn-primary col-sm-2" id="sendBtn">Send</button>
    </div>
</div>

<div class="container" style="margin-top: 30px">


    <div class="table-responsive">
        <table class="table" id="messages">
            <thead>
            <tr class="info">
                <th>#</th>
                <th>Name</th>
                <th>Message</th>
            </tr>
            </thead>
            <tbody id="data"></tbody>
        </table>
    </div>
</div>


</body>
</html>

