<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <c:set var = "currentPage" scope = "session" value = "home.jsp"/>
    <link rel="stylesheet" href="../assets/css/carousel.css">
    <link rel="stylesheet" href="../assets/css/input_form.css">
    <link rel="stylesheet" href="../assets/css/navigation_bar.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="../assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.home" var="home"/>
    <fmt:message bundle="${loc}" key="locale.message.greetings" var="greetings"/>
    <fmt:message bundle="${loc}" key="locale.message.discount" var="discount"/>
    <fmt:message bundle="${loc}" key="locale.message.balance" var="balance"/>
    <fmt:message bundle="${loc}" key="locale.message.account.number" var="account"/>
    <fmt:message bundle="${loc}" key="locale.button.logout" var="logout"/>
    <title> ${home} | ${sessionScope.user.username} | Hostel System</title>
</head>
<body>
<c:choose>
    <c:when test = "${sessionScope.user == null}">
        <jsp:forward page="../index.jsp" />
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
            ${balance}: ${sessionScope.user.balance}
            ${account}: ${sessionScope.user.account}
            <hr>
            <c:choose>
                <c:when test = "${sessionScope.user.status eq 'ADMIN'}">
                    <jsp:include page="/WEB-INF/jsp/button/homeAdmin.jsp"/>
                </c:when>
                <c:when test = "${sessionScope.user.status eq 'USER'}">
                    <jsp:include page="/WEB-INF/jsp/button/homeUser.jsp"/>
                </c:when>
            </c:choose>

            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="command" value="LOGOUT"/>
                <input type="submit" value="${logout}"/>
            </form>
        </div>
    </section>
</div>

<jsp:include page="/WEB-INF/jsp/header/homeHeader.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>