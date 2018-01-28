<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="../../../assets/css/navigation_bar.css">
    <link rel="stylesheet" href="../../../assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.nav.lang" var="lang"/>
    <fmt:message bundle="${loc}" key="locale.nav.lang.ru" var="rus"/>
    <fmt:message bundle="${loc}" key="locale.nav.lang.en" var="eng"/>
    <fmt:message bundle="${loc}" key="locale.nav.home" var="home"/>
    <fmt:message bundle="${loc}" key="locale.button.login" var="login"/>

</head>
<body>
<ul id="navigation_bar">
    <li><a href="${pageContext.request.contextPath}/login">${login}</a></li>
    <li><a href="#">${lang}</a>
        <ul>
            <li><a href="${pageContext.request.contextPath}/hostel_system?command=CHANGE_LOCALE&lang=ru">${rus}</a></li>
            <li><a href="${pageContext.request.contextPath}/hostel_system?command=CHANGE_LOCALE&lang=en">${eng}</a></li>
        </ul>
    </li>
    <li class="active"><a href="${pageContext.request.contextPath}/register">${home}</a></li>
    <li class="system-name"><a>Hostel System</a></li>
</ul>
</body>
</html>