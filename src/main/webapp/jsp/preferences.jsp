<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/carousel.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navigation_bar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.preferences" var="preferences"/>
    <fmt:message bundle="${loc}" key="locale.message.change.data" var="data"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.username" var="username"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.lastname" var="lastname"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.current.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.button.change" var="change"/>
    <fmt:message bundle="${loc}" key="locale.button.delete" var="delete"/>
    <title> ${preferences} | ${sessionScope.user.username} | Hostel System</title>
</head>
<body>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h1>${data}</h1>
            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="command" value="CHANGE_USER_DATA"/>
                <input type="text" name="username" placeholder="${username}" pattern="^[\w]{5,20}$" value="${sessionScope.user.username}"/>
                <input type="text" name="name" placeholder="${name}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.user.name}"/>
                <input type="text" name="lastname" placeholder="${lastname}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.user.lastname}"/>
                <input type="text" name="surname" placeholder="${surname}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.user.surname}"/>
                <input type="email" name="email" placeholder="${email}" pattern="^[\w]+@[A-Za-z]+\.[A-Za-z]{2,3}$" value="${sessionScope.user.email}"/>
                <input type="password" name="password" placeholder="${password}" pattern="^[\w]{6,12}$"/>
                <input type="submit" value="${change}"/>
            </form>
            <form action="${pageContext.request.contextPath}/hostel_system" method="post">
                <c:if test="${requestScope.confirm eq 'true'}">
                    <input type="password" name="confirm-password" placeholder="${password}" pattern="^[\w]{6,12}$"/>
                </c:if>
                <input type="hidden" name="command" value="DELETE_USER"/>
                <input type="submit" value="${delete}"/>
            </form>
            <h2>${requestScope.error}</h2>
        </div>
    </section>
</div>

<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</html>