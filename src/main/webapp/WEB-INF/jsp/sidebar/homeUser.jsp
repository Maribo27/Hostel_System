<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ahs" uri="hostelTag" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.button.show.requests" var="requests"/>
    <fmt:message bundle="${loc}" key="locale.button.add.request" var="request"/>
    <fmt:message bundle="${loc}" key="locale.button.preferences" var="preferences"/>
    <fmt:message bundle="${loc}" key="locale.message.greetings" var="greetings"/>
    <fmt:message bundle="${loc}" key="locale.message.discount" var="discount"/>
    <fmt:message bundle="${loc}" key="locale.message.balance" var="balance"/>
    <fmt:message bundle="${loc}" key="locale.message.account.number" var="account"/>
    <fmt:message bundle="${loc}" key="locale.button.logout" var="logout"/>
</head>
<body>


<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <h3>${greetings}, <ahs:full-name name="${sessionScope.user.personalInfo.name}" surname="${sessionScope.user.personalInfo.surname}" lastName="${sessionScope.user.personalInfo.lastName}"/>!</h3>
            ${discount}: ${sessionScope.user.discount} <br>
            ${balance}: ${sessionScope.user.balance} <br>
            ${account}: ${sessionScope.user.account}
            <hr>
            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="command" value="GET_CITIES"/>
                <input type="submit" value="${request}"/>
            </form>
            <form action="${pageContext.request.contextPath}/preferences">
                <input type="submit" value="${preferences}"/>
            </form>
            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="number" value="1"/>
                <input type="hidden" name="command" value="SHOW_USER_REQUESTS"/>
                <input type="submit" value="${requests}"/>
            </form>
            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="command" value="LOGOUT"/>
                <input type="submit" value="${logout}"/>
            </form>
        </div>
    </section>
</div>
</body>
</html>