<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.nav.lang.ru" var="rus"/>
    <fmt:message bundle="${loc}" key="locale.nav.lang.en" var="eng"/>
</head>
<body>
<ul>
    <li>
        <form action="${pageContext.request.contextPath}/hostel_system" method="post">
            <input type="hidden" name="command" value="CHANGE_LOCALE"/>
            <input type="hidden" name="page" value="${pageContext.request.requestURL}"/>
            <input type="hidden" name="param" value="${pageContext.request.queryString}"/>
            <input type="hidden" name="lang" value="ru"/>
            <input type="submit" value="${rus}"/>
        </form>
    </li>
    <li>
        <form action="${pageContext.request.contextPath}/hostel_system" method="post">
            <input type="hidden" name="command" value="CHANGE_LOCALE"/>
            <input type="hidden" name="page" value="${pageContext.request.requestURL}"/>
            <input type="hidden" name="param" value="${pageContext.request.queryString}"/>
            <input type="hidden" name="lang" value="en"/>
            <input type="submit" value="${eng}"/>
        </form>
    </li>
</ul>
</body>
</html>