<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
</head>
<link href="../../css/container.css" rel="stylesheet">
<link href="../../css/button.css" rel="stylesheet">
<body>
<div class="container">
    <h3><c:out value="${requestScope.user.username}"/></h3>
    <h3><c:out value="${requestScope.user.password}"/></h3>
    <h3><c:out value="${requestScope.user.email}"/></h3>
    <h3><c:out value="${requestScope.user.name}"/></h3>
    <h3><c:out value="${requestScope.user.discount}"/></h3>
    <h3><c:out value="${requestScope.user.status}"/></h3>
    <form action="../../index.jsp">
        <input type="submit" value="Return"/>
    </form>
</div>
</body>
</html>