<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navigation_bar.css">

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
        <jsp:include page="/WEB-INF/jsp/switchLanguage.jsp"/>
    </li>
    <li class="system-name"><a>Hostel System</a></li>
</ul>
</body>
</html>