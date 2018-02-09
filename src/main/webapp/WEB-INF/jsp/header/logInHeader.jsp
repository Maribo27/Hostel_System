<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navigation_bar.css">

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
    <li><a href="${pageContext.request.contextPath}/register">${register}</a></li>
    <li><a href="#">${lang}</a>
        <jsp:include page="/WEB-INF/jsp/switchLanguage.jsp"/>
    </li>
    <li class="system-name"><a>Hostel System</a></li>
</ul>
</body>
</html>