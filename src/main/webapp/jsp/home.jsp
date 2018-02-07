<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.home" var="home"/>
    <fmt:message bundle="${loc}" key="locale.button.logout" var="logout"/>
    <title> ${home} | ${sessionScope.user.personalInfo.username} | Hostel System</title>
</head>
<body>
<c:choose>
    <c:when test = "${empty sessionScope.user}">
        <jsp:forward page="${pageContext.request.contextPath}/register" />
    </c:when>
</c:choose>
<div class="container">

</div>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <c:choose>
                <c:when test = "${sessionScope.user.status eq 'ADMIN'}">
                    <jsp:include page="/WEB-INF/jsp/panel/homeAdmin.jsp"/>
                </c:when>
                <c:when test = "${sessionScope.user.status eq 'USER'}">
                    <jsp:include page="/WEB-INF/jsp/panel/homeUser.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="/WEB-INF/jsp/panel/homeBan.jsp"/>
                </c:otherwise>
            </c:choose>

            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="command" value="LOGOUT"/>
                <input type="submit" value="${logout}"/>
            </form>
        </div>
    </section>
</div>

<jsp:include page="/WEB-INF/jsp/header/homeHeader.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>