<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body onload="setInterval('startRequest()',5000)">
<script>
    var req = null;

    function submitForm() {
        if (window.XMLHttpRequest)
            req = new XMLHttpRequest();
        else if (window.ActiveXObject)
            req = new ActiveXObject(Microsoft.XMLHTTP);
        req.onreadystatechange = handleReq;
        req.open("GET", "simpleResponse.txt?t=" + new Date().getTime(), true);
        req.send(null);
    }

    function handleReq() {
        if (req.readyState == 4)
            if (req.status == 200)
                document.ajax.dyn.value = "Received:" + req.responseText;
            else
                document.ajax.dyn.value = "Error code " + req.status;
    }

    var req2 = null;

    function verifyUser() {
        if (window.XMLHttpRequest)
            req2 = new XMLHttpRequest();
        else if
        (window.ActiveXObject) req2 = new
            ActiveXObject(Microsoft.XMLHTTP);
        req2.onreadystatechange = handleLabelState;
        var yourValue = document.getElementById("user").value;
        var url = "MyServletUrl" + "?uName=" + yourValue + "&timeStamp=" + new Date().getTime();
        req2.open("GET", url, true);
        req2.send(null);
    }

    function handleLabelState() {
        if (req2.readyState == 4 && req2.status == 200) {
            alert( document.getElementById("status").innerHTML);
            var xmlvalue = req2.responseText;
            console.log(xmlvalue);
            document.getElementById("status").innerHTML = xmlvalue;
            alert( document.getElementById("status").innerHTML);

        }
    }

    var xmlHttp;

    function startRequest() {
        createXMLHttpRequest();
        xmlHttp.onreadystatechange = handleStateChange;
        xmlHttp.open("GET", "innerHTML.txt?t=" + new Date().getTime(), true);
        xmlHttp.send(null);
    }

    function createXMLHttpRequest() {
        if (window.ActiveXObject)
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        else if (window.XMLHttpRequest)
            xmlHttp = new XMLHttpRequest();
    }

    function handleStateChange() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            document.getElementById("results").innerHTML = xmlHttp.responseText;
        }
    }

</script>
<center>
    <div>
        <h1>First Screen</h1><br>
        <form action="" method="post" name="ajax">
            <input type="button" value="Get" onclick="submitForm();">
            <input name="dyn" type="text">

        </form>
    </div>
    <br>
    <br>
    <div>
        <h1>SecondScreen</h1>
        <form name="ajax1">
            Name: <input type="text" name="name" id="user" onblur="verifyUser();"><br>
            <label id="status"></label><br>
            Password: <input type="password" name="password"><br><br>
            <input type="submit" value="Submit">
        </form>
    </div>
    <br>
    <br>

    <div>
        <H1>ThirdScreen</H1>
        <div id="results">
        </div>
    </div>
</center>

</body>
</html>
