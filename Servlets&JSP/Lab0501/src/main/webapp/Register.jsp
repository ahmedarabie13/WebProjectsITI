<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <jsp:useBean id="user" scope="request" class="com.arabie.beans.UserBean">
            <jsp:setProperty name="user" property="firstName"/>
            <jsp:setProperty name="user" property="lastName"/>
            <jsp:setProperty name="user" property="password"/>
            <jsp:setProperty name="user" property="userName"/>
        </jsp:useBean>
        <jsp:forward page="Main.jsp"/>
    </body>
</html>
