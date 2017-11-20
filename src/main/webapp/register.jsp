<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<link href="css/container.css" rel="stylesheet">
<link href="css/button.css" rel="stylesheet">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Get Info Page</title>
</head>

<body>
<div class="container">
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="REGISTER"/>
        <input type="text" name="username" placeholder="Enter username" pattern="^[\w]{3,20}$"/>
        <input type="text" name="name" placeholder="Enter name" pattern="^[A-Za-zА-Яа-я]{3,15}$"/>
        <input type="text" name="email" placeholder="Enter email" pattern="^[\w]{3,10}@[A-Za-z]+\.[A-Za-z]{2,3}$"/>
        <input type="text" name="password" placeholder="Enter password" pattern="^[\w]{5,12}$"/>
        <input type="submit" value="Register"/>
    </form>
    <form action="index.jsp">
        <input type="submit" value="Return"/>
    </form>
</div>
</body>
</html>
