<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <c:set var = "currentPage" scope = "session" value = "WEB-INF/jsp/errorPage.jsp"/>
    <link rel="stylesheet" href="../../assets/css/carousel.css">
    <link rel="stylesheet" href="../../assets/css/input_form.css">
    <link rel="stylesheet" href="../../assets/css/navigation_bar.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="../../assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.error" var="error"/>
    <fmt:message bundle="${loc}" key="locale.nav.home" var="home"/>
    <title> ${error} | ${sessionScope.user.username} | Hostel System</title>
</head>

<body>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h1>${error}!</h1>
            <h3><c:out value="${requestScope.error}"/></h3>
            <form action="../../index.jsp">
                <input type="submit" value="${home}"/>
            </form>
        </div>
    </section>
</div>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>