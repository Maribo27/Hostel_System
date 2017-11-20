<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User data</title>
</head>
<link href="../../css/button.css" rel="stylesheet">
<link href="../../css/container.css" rel="stylesheet">
<body>
<div class="container">
    <h1>Error occurred!</h1>
    <h3><c:out value="${requestScope.error}"/></h3>
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="LOGIN"/>
        <input type="text" name="username" value="${requestScope.username}" placeholder="Enter username or email" pattern="([\w]{3,10}@[A-Za-z]+\.[A-Za-z]{2,3}|[\w]{3,20})"/>
        <input type="password" name="password" placeholder="Enter password" pattern="^[\w]{5,12}$"/>
        <input type="submit" value="Log In"/>
    </form>
    <form action="../../index.jsp">
        <input type="submit" value="Return"/>
    </form>
</div>
</body>
</html>
