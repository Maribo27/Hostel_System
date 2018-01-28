<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.button.approve" var="approve"/>
    <fmt:message bundle="${loc}" key="locale.button.deny" var="deny"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/hostel_system" method="get">
    <input type="hidden" name="command" value="CHANGE_REQUEST_STATUS"/>
    <input type="hidden" name="request" value="${param.id}"/>
    <input type="hidden" name="status" value="approved"/>
    <input type="submit" value="${approve}"/>
</form>
<form action="${pageContext.request.contextPath}/hostel_system" method="get">
    <input type="hidden" name="command" value="CHANGE_REQUEST_STATUS"/>
    <input type="hidden" name="request" value="${param.id}"/>
    <input type="hidden" name="status" value="denied"/>
    <input type="submit" value="${deny}"/>
</form>
</body>
</html>