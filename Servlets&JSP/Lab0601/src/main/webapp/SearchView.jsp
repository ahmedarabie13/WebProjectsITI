<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <title>Search</title>
	</head>

	<body>
	    <%@ page import="java.util.List" %>
	    <%@ page import="com.arabie.entities.UserEntity" %>
	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
	    <c:set var="dataSourceSql" value="${DataSource}"/>
		<c:if test="${empty applicationScope.DataSource}">
			<c:out value="dataSource is empty"/>
		</c:if>
	    <center>
	    <BR><h1><b>Search Form</b></h1>
	        <FORM action="SearchServletUrl" method= GET>
	        <br><h3>Please Enter First Name,Last Name or any part of them.</h3>
	        <b>Name: </b> <INPUT TYPE=TEXT NAME=name>
	        <INPUT TYPE=SUBMIT VALUE=Submit>
	        </FORM><BR><BR>
	    </center>
	    <c:set var="usersList" value="${requestScope.SearchedUsers}"/>

	   
	    <c:if test="${not empty requestScope.SearchedUsers}">
		    <table style="width: 90%;text-align: center;">
		        <tr>
		            <th>First Name</th>
		            <th>Last Name</th>
		            <th>UserName</th>
		            <th>Password</th>
		        </tr>
				<c:forEach items="${usersList}" var="user">
					<tr>
			            <td><c:out value="${user.firstName}"/></td>
			            <td><c:out value="${user.lastName}"/></td>
			            <td><c:out value="${user.userName}"/></td>
			            <td><c:out value="${user.password}"/></td>
		        	</tr>
				</c:forEach>
			</table>
	    </c:if>
	   
	    <CENTER>
    	    <h2><b>Select the Password of userName = aGamal</b></h2>
    	    <sql:query var="resultSet" dataSource="${dataSourceSql}" sql="SELECT password FROM user where user_name='aGamal'"/>
    	    <c:forEach items="${resultSet.getRows()}" var="result">
    	    	<c:out value="${result.password}"/>
	    	</c:forEach>
    	</CENTER>
	</body>
</html>
