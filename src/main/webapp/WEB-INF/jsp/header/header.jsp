<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dropdowns.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/dropdowns-skin-discrete.css">
    <script src="${pageContext.request.contextPath}/assets/js/support/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/dropdowns.js"></script>

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.message.balance" var="balance"/>
    <fmt:message bundle="${loc}" key="locale.nav.lang" var="lang"/>
    <fmt:message bundle="${loc}" key="locale.nav.home" var="home"/>
    <fmt:message bundle="${loc}" key="locale.nav.menu" var="menu"/>
    <fmt:message bundle="${loc}" key="locale.button.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="locale.nav.show" var="action"/>
    <fmt:message bundle="${loc}" key="locale.nav.show.users" var="users"/>
    <fmt:message bundle="${loc}" key="locale.nav.show.hostels" var="hostels"/>
    <fmt:message bundle="${loc}" key="locale.nav.show.requests" var="requests"/>
</head>
<body>
<div class="dropdowns">
    <a class="toggleMenu" href="#" style="display: none;">${menu}</a>
    <ul class="nav" style="display: block;">
        <li class="system-name"><a href="#">Hostel System</a></li>

        <li class=""><a href="${pageContext.request.contextPath}/hostel_system?command=LOGOUT">${logout}</a></li>
        <li class="">
            <a href="#" class="parent">${lang}</a>
            <jsp:include page="/WEB-INF/jsp/switchLanguage.jsp"/>
        </li>
        <li class=""><a href="${pageContext.request.contextPath}/home">${home}</a></li>
        <c:choose>
            <c:when test="${sessionScope.user.status eq 'ADMIN'}">
                <li class="">
                    <a href="#" class="parent">${action}</a>
                    <ul>
                        <li class="">
                            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_USERS"/>
                                <input type="submit" value="${users}"/>
                            </form>
                        </li>
                        <li class="">
                            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_HOSTELS"/>
                                <input type="submit" value="${hostels}"/>
                            </form>
                        </li>
                        <li class="">
                            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_REQUESTS"/>
                                <input type="submit" value="${requests}"/>
                            </form>
                        </li>
                    </ul>
                </li>
            </c:when>
            <c:otherwise>
                <li class="balance"><a href="#">${balance} : ${sessionScope.user.balance}</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<script>
    $(".dropdowns").dropdowns();
</script>
<div style="padding:20px;"></div>
</body>
</html>