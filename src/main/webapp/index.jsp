<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<link href="css/container.css" rel="stylesheet">
<link href="css/button.css" rel="stylesheet">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Log In</title>
</head>

<body>
<div class="container">
    <form action="Controller" method="get">
        <input type="hidden" name="command" value="LOGIN"/>
        <input type="text" name="username" placeholder="Enter username or email" pattern="([\w]{3,10}@[A-Za-z]+\.[A-Za-z]{2,3}|[\w]{3,20})"/>
        <input type="password" name="password" placeholder="Enter password" pattern="^[\w]{5,12}$"/>
        <input type="submit" value="Log In"/>
    </form>
    <form action="register.jsp">
        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>
