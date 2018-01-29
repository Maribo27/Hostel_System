<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.button.cancel" var="cancel"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/hostel_system" method="get">
    <input type="hidden" name="command" value="CANCEL_REQUEST"/>
    <input type="hidden" name="next-command" value="SHOW_USER_REQUESTS"/>
    <input type="hidden" name="number" value="${param.page}"/>
    <input type="hidden" name="request" value="${param.requestId}"/>
    <input type="hidden" name="id" value="${param.userId}"/>
    <input type="hidden" name="status" value="deleted"/>
    <input type="submit" value="${cancel}"/>
</form>
</body>
</html>