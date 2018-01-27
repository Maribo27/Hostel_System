<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <c:set var = "currentPage" scope = "session" value = "login.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="assets/css/carousel.css">
    <link rel="stylesheet" href="assets/css/input_form.css">
    <link rel="stylesheet" href="assets/css/navigation_bar.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="assets/images/favicon.png">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.not.account" var="account"/>
    <fmt:message bundle="${loc}" key="locale.message.click.register" var="click"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.data" var="data"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.username.or.email" var="username"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.button.login" var="login"/>
    <title>Hostel System</title>
</head>

<body>
<c:choose>
    <c:when test = "${sessionScope.user != null}">
        <jsp:forward page="/home" />
    </c:when>
</c:choose>
<div style="padding:20px;"></div>

<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h1>${data}</h1>
            <form action="${pageContext.request.contextPath}/hostel_system" method="post">
                <input type="hidden" name="command" value="LOGIN"/>
                <input type="text" name="username" value="${requestScope.username}" placeholder="${username}" pattern="([\w\.]{3,10}@[A-Za-z]+\.[A-Za-z]{2,3}|[\w\.]{3,20})"/>
                <input type="password" name="password" placeholder="${password}" pattern="^[\w]{5,12}$"/>
                <input type="submit" value="Log In"/>
            </form>
        </div>
        <div class="input-data-form-help">
            <p>${account}? <a href="${pageContext.request.contextPath}/register">${click}</a>.</p>
        </div>
    </section>
</div>


<jsp:include page="/WEB-INF/jsp/carousel.jsp"/>
<jsp:include page="/WEB-INF/jsp/logInHeader.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>