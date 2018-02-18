<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/error.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.button.return" var="back"/>
    <title>Hostel System</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<div class="error">
    <p class="message">${requestScope.error}</p>
    <a href="${pageContext.request.contextPath}/index.jsp">${back}</a>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>