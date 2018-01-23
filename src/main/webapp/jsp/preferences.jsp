<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <c:set var = "currentPage" scope = "session" value = "jsp/preferences.jsp"/>
    <link rel="stylesheet" href="../assets/css/carousel.css">
    <link rel="stylesheet" href="../assets/css/input_form.css">
    <link rel="stylesheet" href="../assets/css/navigation_bar.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="../assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.preferences" var="preferences"/>
    <fmt:message bundle="${loc}" key="locale.message.change.data" var="data"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.username" var="username"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.lastname" var="lastname"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.button.change" var="change"/>
    <title> ${preferences} | ${sessionScope.user.username} | Hostel System</title>
</head>
<body>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h1>${data}</h1>
            <form action="Controller" method="get">
                <input type="hidden" name="command" value="CHANGE_USER_DATA"/>
                <input type="text" name="username" placeholder="${username}" pattern="^[\w]{5,20}$" value="${sessionScope.username}"/>
                <input type="text" name="name" placeholder="${name}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.name}"/>
                <input type="text" name="lastname" placeholder="${lastname}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.lastname}"/>
                <input type="text" name="surname" placeholder="${surname}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.surname}"/>
                <input type="email" name="email" placeholder="${email}" pattern="^[\w]+@[A-Za-z]+\.[A-Za-z]{2,3}$" value="${sessionScope.email}"/>
                <input type="password" name="password" placeholder="${password}" pattern="^[\w]{6,12}$"/>
                <input type="submit" value="${change}"/>
            </form>
        </div>
    </section>
</div>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</html>