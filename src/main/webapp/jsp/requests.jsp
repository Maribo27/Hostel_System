<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.requests" var="requests"/>
    <title> ${requests} | ${sessionScope.user.personalInfo.username} | Hostel System</title>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<c:choose>
    <c:when test = "${sessionScope.user.status eq 'ADMIN'}">
        <jsp:include page="/WEB-INF/jsp/table/allRequests.jsp">
            <jsp:param name="command" value="SHOW_REQUESTS"/>
        </jsp:include>
    </c:when>
    <c:when test="${sessionScope.user.status eq 'USER'}">
        <jsp:include page="/WEB-INF/jsp/table/userRequests.jsp">
            <jsp:param name="command" value="SHOW_USER_REQUESTS"/>
        </jsp:include>
    </c:when>
</c:choose>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
<jsp:include page="/WEB-INF/jsp/topButton.jsp"/>
</body>
</html>