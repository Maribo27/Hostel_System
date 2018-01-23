<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <c:set var = "currentPage" scope = "session" value = "jsp/home.jsp"/>
    <link rel="stylesheet" href="assets/css/carousel.css">
    <link rel="stylesheet" href="assets/css/input_form.css">
    <link rel="stylesheet" href="assets/css/navigation_bar.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.home" var="home"/>
    <fmt:message bundle="${loc}" key="locale.message.greetings" var="greetings"/>
    <fmt:message bundle="${loc}" key="locale.message.discount" var="discount"/>
    <fmt:message bundle="${loc}" key="locale.button.show.users" var="users"/>
    <fmt:message bundle="${loc}" key="locale.button.show.hostels" var="hostels"/>
    <fmt:message bundle="${loc}" key="locale.button.show.requests" var="requests"/>
    <fmt:message bundle="${loc}" key="locale.button.add.request" var="request"/>
    <fmt:message bundle="${loc}" key="locale.button.preferences" var="preferences"/>
    <fmt:message bundle="${loc}" key="locale.button.logout" var="logout"/>
    <title> ${home} | ${sessionScope.user.username} | Hostel System</title>
</head>
<body>
<c:choose>
    <c:when test = "${sessionScope.user == null}">
        <jsp:forward page="index.jsp" />
    </c:when>
</c:choose>
<div class="container">

</div>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h3>${greetings}, ${sessionScope.user.name}!</h3>
            ${discount}: ${sessionScope.user.discount}
            <hr>
            <c:choose>
                <c:when test = "${sessionScope.user.status.toString() eq 'admin'}">
                    <form action="Controller" method="get">
                        <input type="hidden" name="number" value="1"/>
                        <input type="hidden" name="command" value="SHOW_USERS"/>
                        <input type="submit" value="${users}"/>
                    </form>
                    <form action="Controller" method="get">
                        <input type="hidden" name="number" value="1"/>
                        <input type="hidden" name="command" value="SHOW_HOSTELS"/>
                        <input type="submit" value="${hostels}"/>
                    </form>
                    <form action="Controller" method="get">
                        <input type="hidden" name="number" value="1"/>
                        <input type="hidden" name="command" value="SHOW_REQUESTS"/>
                        <input type="submit" value="${requests}"/>
                    </form>
                </c:when>
                <c:when test = "${sessionScope.user.status.toString() eq 'user'}">
                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="SHOW_CREATING_FORM"/>
                        <input type="submit" value="${request}"/>
                    </form>
                    <form action="jsp/preferences.jsp">
                        <input type="submit" value="${preferences}"/>
                    </form>
                    <form action="Controller" method="get">
                        <input type="hidden" name="number" value="1"/>
                        <input type="hidden" name="command" value="SHOW_USER_REQUESTS"/>
                        <input type="submit" value="${requests}"/>
                    </form>
                </c:when>
            </c:choose>

            <form action="Controller" method="get">
                <input type="hidden" name="command" value="LOGOUT"/>
                <input type="submit" value="${logout}"/>
            </form>
        </div>
    </section>
</div>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>