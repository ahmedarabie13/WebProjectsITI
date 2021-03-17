<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
    <%@ page import="java.util.List" %>
    <%@ page import="com.arabie.entities.UserEntity" %>
<%--    <%@ page import="jakarta.servlet.jsp.*" %>--%>
    <center>
    <BR><h1><b>Search Form</b></h1>
        <FORM action="SearchServletUrl" method= GET>
        <br><h3>Please Enter First Name,Last Name or any part of them.</h3>
        <b>Name: </b> <INPUT TYPE=TEXT NAME=name>
        <INPUT TYPE=SUBMIT VALUE=Submit>
        </FORM><BR><BR>
    </center>
    <%
//        var pageContext1 = JspFactory.getDefaultFactory().getPageContext(null,request,response,"",false,0,false);
//        out.println(pageContext1.getClass());
        List<UserEntity> retrievedUsers = (List<UserEntity>) request.getAttribute("SearchedUsers");
        if(retrievedUsers!=null){
    %>
    <table style="width: 90%;text-align: center;">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>UserName</th>
            <th>Password</th>
        </tr>
        <%for (UserEntity user : retrievedUsers) { %>
        <tr>
            <td><% out.println(user.getFirstName());  %></td>
            <td><% out.println(user.getLastName());  %></td>
            <td><% out.println(user.getUserName());  %></td>
            <td><% out.println(user.getPassword());  %></td>
        </tr>
        <%}%>
    </table>
    <%}%>

</body>
</html>
