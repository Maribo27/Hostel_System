<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <form id="login" action="${pageContext.request.contextPath}/hostel_system" method="post">
                <input type="hidden" name="command" value="LOGIN"/>
                <label for="username">
                    <input type="text" id="username" name="username" placeholder="${username}" pattern="^[\w][\w\.\_\d]{4,20}$" value="${sessionScope.user.username}" maxlength="20" minlength="5" required>
                    <ul class="input-requirements">
                        <li>At least 5 characters long (and less than 20 characters)</li>
                        <li>First symbol - letter</li>
                        <li>Must only contain latin letters, numbers, "." and "_"</li>
                    </ul>
                </label>
                <label for="password">
                    <input type="password" id="password" name="password" placeholder="${password}" pattern="^[\w\d\.\_]{5,12}$" maxlength="12" minlength="5" required/>
                    <ul class="input-requirements">
                        <li>At least 5 characters long (and less than 12 characters)</li>
                        <li>Must only contain latin letters, numbers, "_" and "."</li>
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
<script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>



<jsp:include page="/WEB-INF/jsp/carousel.jsp"/>
<jsp:include page="/WEB-INF/jsp/header/logInHeader.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>