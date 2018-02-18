<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.hostels" var="hostels"/>
    <title> ${hostels} | ${sessionScope.user.personalInfo.username} | Hostel System</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<c:choose>
    <c:when test="${sessionScope.user.status eq 'ADMIN'}">
        <c:if test="${fn:length(requestScope.hostels) gt 0}">
            <jsp:include page="/WEB-INF/jsp/table/allHostels.jsp"/>
        </c:if>
    </c:when>
    <c:when test="${sessionScope.user.status eq 'USER'}">
        <jsp:include page="/WEB-INF/jsp/chooseParameters.jsp"/>
        <c:if test="${fn:length(requestScope.hostels) gt 0}">
            <jsp:include page="/WEB-INF/jsp/table/requestHostels.jsp"/>
        </c:if>
    </c:when>
</c:choose>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
<jsp:include page="/WEB-INF/jsp/topButton.jsp"/>
</body>
</html>