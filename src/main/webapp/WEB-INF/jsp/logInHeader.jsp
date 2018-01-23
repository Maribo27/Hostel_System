<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="../../assets/css/navigation_bar.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.nav.lang" var="lang"/>
    <fmt:message bundle="${loc}" key="locale.nav.lang.ru" var="rus"/>
    <fmt:message bundle="${loc}" key="locale.nav.lang.en" var="eng"/>
    <fmt:message bundle="${loc}" key="locale.nav.home" var="home"/>
    <fmt:message bundle="${loc}" key="locale.button.register" var="register"/>

</head>
<body>
<ul id="navigation_bar">
    <li><a href="../../index.jsp">${register}</a></li>
    <li><a href="#">${lang}</a>
        <ul>
            <li><a href="Controller?command=CHANGE_LOCALE&lang=ru">${rus}</a></li>
            <li><a href="Controller?command=CHANGE_LOCALE&lang=en">${eng}</a></li>
        </ul>
    </li>
    <li class="active"><a href="../../home.jsp">${home}</a></li>
    <li class="system-name"><a>Hostel System</a></li>
</ul>
</body>
</html>