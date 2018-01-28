<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>

<head>
    <c:set var = "currentPage" scope = "session" value = "index.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="assets/css/carousel.css">
    <link rel="stylesheet" href="assets/css/input_form.css">
    <link rel="stylesheet" href="assets/css/navigation_bar.css">
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
        <jsp:forward page="jsp/home.jsp" />
    </c:when>
</c:choose>
<div style="padding:20px;"></div>


<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h1>${data}</h1>
            <form action="${pageContext.request.contextPath}/hostel_system" method="post">
                <input type="hidden" name="command" value="REGISTER"/>
                        <input type="hidden" name="lang" value="en"/>
                        <input type="text" name="username" placeholder="${username}" pattern="^[\w]{5,20}$" value="${requestScope.username}"/>
                        <input type="text" name="name" placeholder="${name}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${requestScope.name}"/>
                        <input type="text" name="lastname" placeholder="${lastname}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${requestScope.lastname}"/>
                        <input type="text" name="surname" placeholder="${surname}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${requestScope.surname}"/>
                        <input type="email" name="email" placeholder="${email}" pattern="^[\w]+@[A-Za-z]+\.[A-Za-z]{2,3}$" value="${requestScope.email}"/>
                        <input type="password" name="password" placeholder="${password}" pattern="^[\w]{6,12}$"/>
                        <input type="submit" value="${register}"/>
            </form>
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
