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
    <h1>Error occurred!</h1>
    <h3><c:out value="${requestScope.error}"/></h3>
    <form action="../../index.jsp">
        <input type="submit" value="Return"/>
    </form>
</div>
</body>
</html>