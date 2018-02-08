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
    <fmt:message bundle="${loc}" key="locale.message.enter.repeat.password" var="repeat"/>
    <fmt:message bundle="${loc}" key="locale.button.register" var="register"/>

    <fmt:message bundle="${loc}" key="locale.input.message.user.content" var="userContent"/>
    <fmt:message bundle="${loc}" key="locale.input.message.username.length" var="usernameLength"/>
    <fmt:message bundle="${loc}" key="locale.input.message.password.length" var="passwordLength"/>
    <fmt:message bundle="${loc}" key="locale.input.message.name.first" var="nameFirst"/>
    <fmt:message bundle="${loc}" key="locale.input.message.name.length" var="nameLength"/>
    <fmt:message bundle="${loc}" key="locale.input.message.name.content" var="nameContent"/>
    <fmt:message bundle="${loc}" key="locale.input.message.email.content" var="emailContent"/>
    <fmt:message bundle="${loc}" key="locale.input.message.email.symbol" var="emailSymbol"/>

    <title>Hostel System</title>
</head>

<body>
<c:choose>
    <c:when test = "${not empty sessionScope.user}">
        <jsp:forward page="${pageContext.request.contextPath}/home" />
    </c:when>
</c:choose>
<div style="padding:20px;"></div>


<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h1>${data}</h1>
            <c:if test="${empty sessionScope.lang}">
                <c:set var="lang" scope="session" value="ru"/>
            </c:if>
            <form id="registration" accept-charset="UTF-8" action="${pageContext.request.contextPath}/hostel_system" method="post">
                <label for="username">
                    <input type="text" id="username" name="username" placeholder="${username}" pattern="^[\w][\w\.\_\d]{4,20}$" value="${sessionScope.user.personalInfo.username}" maxlength="20" minlength="5" required>
                    <ul class="input-requirements">
                        <li>${usernameLength}</li>
                        <li>${userContent}</li>
                    </ul>
                </label>

                <label for="name">
                    <input type="text" id="name" name="name" placeholder="${name}" pattern="^[A-ZА-Я][a-zа-я]{1,50}$" value="${sessionScope.user.personalInfo.name}" maxlength="50" minlength="2" required/>
                    <ul class="input-requirements">
                        <li>${nameLength}</li>
                        <li>${nameFirst}</li>
                        <li>${nameContent}</li>
                    </ul>
                </label>

                <label for="lastname">
                    <input type="text" id="lastname" name="lastname" placeholder="${lastname}" maxlength="50" value="${sessionScope.user.personalInfo.lastname}"/>
                    <ul class="input-requirements">
                        <li>${nameLength}</li>
                        <li>${nameFirst}</li>
                        <li>${userContent}</li>
                    </ul>
                </label>

                <label for="surname">
                    <input type="text" id="surname" name="surname" placeholder="${surname}" pattern="^[A-ZА-Я][a-zа-я]{1,50}$" value="${sessionScope.user.personalInfo.surname}" maxlength="50" minlength="2" required/>
                    <ul class="input-requirements">
                        <li>${nameLength}</li>
                        <li>${nameFirst}</li>
                        <li>${userContent}</li>
                    </ul>
                </label>

                <label for="email">
                    <input type="email" id="email" name="email" placeholder="${email}" pattern="^[\w\.\_\d\-]+@[A-Za-z]+.[A-Za-z]{2,3}$" value="${sessionScope.user.personalInfo.email}" required/>
                    <ul class="input-requirements">
                        <li>${emailContent}</li>
                        <li>${emailSymbol}</li>
                    </ul>
                </label>

                <label for="password">
                    <input type="password" id="password" name="password" placeholder="${password}" pattern="^[\w\d\.\_]{5,12}$" maxlength="12" minlength="5" required/>
                    <ul class="input-requirements">
                        <li>${passwordLength}</li>
                        <li>${userContent}</li>
                    </ul>
                </label>

                <label for="password-repeat">
                    <input type="password" id="password-repeat" placeholder="${repeat}" maxlength="12" minlength="5" required>
                </label>

                <input type="hidden" name="command" value="REGISTER"/>
                <input type="submit" value="${register}"/>
            </form>
            <script src="assets/js/validation/validation_${sessionScope.lang}.js"></script>
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
