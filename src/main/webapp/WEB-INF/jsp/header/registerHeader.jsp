<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dropdowns.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dropdowns-skin-discrete.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/dropdowns.js"></script>

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.nav.lang" var="lang"/>
    <fmt:message bundle="${loc}" key="locale.nav.lang.ru" var="rus"/>
    <fmt:message bundle="${loc}" key="locale.nav.lang.en" var="eng"/>
    <fmt:message bundle="${loc}" key="locale.nav.home" var="home"/>
    <fmt:message bundle="${loc}" key="locale.nav.menu" var="menu"/>
    <fmt:message bundle="${loc}" key="locale.button.login" var="login"/>

</head>
<body>
<div class="dropdowns">
    <a class="toggleMenu" href="#" style="display: none;">${menu}</a>
    <ul class="nav" style="display: block;">
        <li class="system-name"><a href="#">Hostel System</a></li>

        <li class=""><a href="${pageContext.request.contextPath}/login">${login}</a></li>
        <li class="">
            <a href="#" class="parent">${lang}</a>
            <jsp:include page="/WEB-INF/jsp/switchLanguage.jsp"/>
        </li>
    </ul>
</div>
<script>
    $(".dropdowns").dropdowns();
</script>
<div style="padding:20px;"></div>
</body>
</html>