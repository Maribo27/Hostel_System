<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="assets/css/input_form.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="assets/images/favicon.png">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.account" var="account"/>
    <fmt:message bundle="${loc}" key="locale.message.click.login" var="click"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.registration.data" var="data"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.username" var="username"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.lastname" var="lastname"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.button.register" var="register"/>
    <title>Hostel System</title>
</head>

<body>
<c:choose>
    <c:when test = "${sessionScope.user != null}">
        <jsp:forward page="${pageContext.request.contextPath}/home" />
    </c:when>
</c:choose>
<div style="padding:20px;"></div>


<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h1>${data}</h1>
            <form id="registration" action="${pageContext.request.contextPath}/hostel_system" method="post">
                <label for="username">
                    <input type="text" id="username" name="username" placeholder="${username}" pattern="^[\w][\w\.\_\d]{4,20}$" value="${sessionScope.user.username}" maxlength="20" minlength="5" required>
                    <ul class="input-requirements">
                        <li>At least 5 characters long (and less than 20 characters)</li>
                        <li>First symbol - letter</li>
                        <li>Must only contain latin letters, numbers, "." and "_"</li>
                    </ul>
                </label>

                <label for="name">
                    <input type="text" id="name" name="name" placeholder="${name}" pattern="^[A-ZА-Я][a-zа-я]{1,50}$" value="${sessionScope.user.name}" maxlength="50" minlength="2" required/>
                    <ul class="input-requirements">
                        <li>At least 2 characters long (and less than 50 characters)</li>
                        <li>Must only contain letters</li>
                        <li>First symbol - uppercase letter</li>
                    </ul>
                </label>

                <label for="lastname">
                    <input type="text" id="lastname" name="lastname" placeholder="${lastname}" pattern="^[A-ZА-Я][a-zа-я]{1,50}$" value="${sessionScope.user.lastname}" maxlength="50" minlength="2"/>
                    <ul class="input-requirements">
                        <li>At least 2 characters long (and less than 50 characters)</li>
                        <li>Must only contain letters</li>
                        <li>First symbol - uppercase letter</li>
                    </ul>
                </label>

                <label for="surname">
                    <input type="text" id="surname" name="surname" placeholder="${surname}" pattern="^[A-ZА-Я][a-zа-я]{1,50}$" value="${sessionScope.user.surname}" maxlength="50" minlength="2" required/>
                    <ul class="input-requirements">
                        <li>At least 2 characters long (and less than 50 characters)</li>
                        <li>Must only contain letters</li>
                        <li>First symbol - uppercase letter</li>
                    </ul>
                </label>

                <label for="email">
                    <input type="email" id="email" name="email" placeholder="${email}" pattern="^[\w\.\_\d\-]+@[A-Za-z]+.[A-Za-z]{2,3}$" value="${sessionScope.user.email}" required/>
                    <ul class="input-requirements">
                        <li>Must only contain latin letters, numbers, "_", "-" and "."</li>
                        <li>Contains "@"</li>
                    </ul>
                </label>

                <label for="password">
                    <input type="password" id="password" name="password" placeholder="${password}" pattern="^[\w\d\.\_]{5,12}$" maxlength="12" minlength="5" required/>
                    <ul class="input-requirements">
                        <li>At least 5 characters long (and less than 12 characters)</li>
                        <li>Must only contain latin letters, numbers, "_" and "."</li>
                    </ul>
                </label>

                <label for="password_repeat">
                    <span>Repeat Password</span>
                    <input type="password" id="password_repeat" maxlength="12" minlength="5" required>
                </label>

                <input type="hidden" name="command" value="REGISTER"/>
                <input type="submit" value="${register}"/>
            </form>

            <script src="assets/js/validation.js"></script>
            <h2>${requestScope.error}</h2>
        </div>
        <div class="input-data-form-help">
            <p>${account}? <a href="${pageContext.request.contextPath}/login">${click}</a>.</p>
        </div>
    </section>
</div>

<div style="padding:15px;"></div>

<jsp:include page="/WEB-INF/jsp/carousel.jsp"/>
<jsp:include page="/WEB-INF/jsp/header/registerHeader.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
