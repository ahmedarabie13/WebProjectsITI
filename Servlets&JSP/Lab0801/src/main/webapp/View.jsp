<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1256">
<title>Welcome Page</title>
</head>
<body>
    <center>
	<form action="DownloadServletUrl" method="POST">
        <input type="submit" value="Download Data.txt">
    </form>
    <br>
    <br>
    <form method="POST" action="UploadServletUrl" enctype="multipart/form-data" >
        File:
        <input type="file" name="file" id="file" /> <br/>
        
        <input type="submit" value="Upload" name="upload" id="upload" />
    </form>
    </center>
</body>
</html>