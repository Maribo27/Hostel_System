<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
</head>
<link href="../../css/container.css" rel="stylesheet">
<link href="../../css/button.css" rel="stylesheet">
<body>

<div class="container">
    <h1>Error!</h1>
    <h3><c:out value="${requestScope.error}"/></h3>
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="REGISTER"/>
        <input type="text" name="username" placeholder="Enter username" pattern="^[\w]{3,20}$" value="${requestScope.username}"/>
        <input type="text" name="name" placeholder="Enter name" pattern="^[A-Za-zА-Яа-я]{3,15}$" value="${requestScope.name}"/>
        <input type="text" name="email" placeholder="Enter email" pattern="^[\w]{3,10}@[A-Za-z]+\.[A-Za-z]{2,3}$" value="${requestScope.email}"/>
        <input type="text" name="password" placeholder="Enter password" pattern="^[\w]{5,12}$"/>
        <input type="submit" value="Register"/>
    </form>
    <form action="../../index.jsp">
        <input type="submit" value="Return"/>
    </form>
</div>
</body>
</html>