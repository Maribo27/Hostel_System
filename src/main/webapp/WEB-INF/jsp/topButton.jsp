<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/topButton.css">
    <script src="${pageContext.request.contextPath}/assets/js/support/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/backToTop.js"></script>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.button.top" var="toTop"/>
</head>
<body>
<div id = "toTop" >^ ${toTop}</div>
</body>
</html>