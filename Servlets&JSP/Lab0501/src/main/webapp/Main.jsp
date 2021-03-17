<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
   <center>
       <jsp:useBean id="user" class="com.arabie.beans.UserBean" scope="request"/>
       <h1>User Data</h1><br><br><br>
       <b>UserName: </b><jsp:getProperty name="user" property="userName"/><br><br>
       <b>First Name: </b><jsp:getProperty name="user" property="firstName"/><br><br>
       <b>Last Name: </b><jsp:getProperty name="user" property="lastName"/><br><br>
       <b>Password: </b><jsp:getProperty name="user" property="password"/><br><br>
   </center>
</body>
</html>
