<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.not.account" var="account"/>
    <fmt:message bundle="${loc}" key="locale.message.click.register" var="click"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.data" var="data"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.username.or.email" var="username"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.button.login" var="login"/>

    <fmt:message bundle="${loc}" key="locale.input.message.user.content" var="userContent"/>
    <fmt:message bundle="${loc}" key="locale.input.message.username.length" var="usernameLength"/>
    <fmt:message bundle="${loc}" key="locale.input.message.password.length" var="passwordLength"/>

    <title>Hostel System</title>
</head>
<body>
<c:choose>
    <c:when test = "${not empty sessionScope.user}">
        <jsp:forward page="/home" />
    </c:when>
</c:choose>
<jsp:include page="/WEB-INF/jsp/header/logInHeader.jsp"/>
<jsp:include page="/WEB-INF/jsp/information.jsp"/>
<div class="sidebar-index">
    <section class="container">
        <div class="input-data-form">
            <h1>${data}</h1>
            <form id="login" action="${pageContext.request.contextPath}/hostel_system" method="post">
                <input type="hidden" name="command" value="LOGIN"/>
                <label for="username">
                    <input type="text" id="username" name="username" placeholder="${username}" pattern="^[\w][\w\.\_\d]{4,20}$" value="${sessionScope.user.personalInfo.username}" maxlength="20" minlength="5" required>
                    <ul class="input-requirements">
                        <li>${usernameLength}</li>
                        <li>${userContent}</li>
                    </ul>
                </label>
                <label for="password">
                    <input type="password" id="password" name="password" placeholder="${password}" pattern="^[\w\d\.\_]{5,12}$" maxlength="12" minlength="5" required/>
                    <ul class="input-requirements">
                        <li>${passwordLength}</li>
                        <li>${userContent}</li>
                    </ul>
                </label>
                <input type="submit" value="${login}"/>
            </form>
            <h2>${requestScope.error}</h2>
        </div>
        <div class="input-data-form-help">
            <p>${account}? <a href="${pageContext.request.contextPath}/register">${click}</a>.</p>
        </div>
    </section>
</div>
<script src="assets/js/validation/validation_${sessionScope.lang}.js"></script>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
<jsp:include page="/WEB-INF/jsp/topButton.jsp"/>
</body>
</html>