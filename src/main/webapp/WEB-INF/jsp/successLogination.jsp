<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Info</title>
</head>
<link href="../../css/container.css" rel="stylesheet">
<link href="../../css/button.css" rel="stylesheet">
<body>
<div class="container">
    <h3>Welcome back, <c:out value="${requestScope.user.name}"/>!</h3>
    <hr>
    Your email: <c:out value="${requestScope.user.email}"/>
    <br>
    Your personal discount: <c:out value="${requestScope.user.discount}"/>%
    <br>
    Your status: <c:out value="${requestScope.user.status}"/>
    <hr>
    <form action="../../index.jsp">
        <input type="submit" value="Log out"/>
    </form>
</div>
</body>
</html>