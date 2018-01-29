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
    <fmt:message bundle="${loc}" key="locale.message.enter.new.password" var="newPassword"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.current.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.button.change" var="change"/>
    <title> ${preferences} | ${sessionScope.user.username} | Hostel System</title>
</head>
<body>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h1>${data}</h1>
            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="command" value="CHANGE_PASSWORD"/>
                <input type="text" name="new-password" placeholder="${newPassword}" pattern="^[\w]{6,12}$"/>
                <input type="password" name="password" placeholder="${password}" pattern="^[\w]{6,12}$"/>
                <input type="submit" value="${change}"/>
            </form>
        </div>
    </section>
</div>

<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</html>