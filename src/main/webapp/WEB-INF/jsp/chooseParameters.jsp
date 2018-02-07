<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.booking" var="bookingPage"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.rooms" var="rooms"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.days" var="days"/>
    <fmt:message bundle="${loc}" key="locale.button.return" var="return"/>
    <fmt:message bundle="${loc}" key="locale.button.block" var="block"/>
    <fmt:message bundle="${loc}" key="locale.button.search" var="search"/>
    <fmt:message bundle="${loc}" key="locale.list.booking" var="booking"/>
    <fmt:message bundle="${loc}" key="locale.list.payment" var="payment"/>
    <title> ${bookingPage} | ${sessionScope.user.personalInfo.username} | Hostel System</title>
</head>

<body>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <form action="${pageContext.request.contextPath}/hostel_system" method="post">
                <input type="hidden" name="command" value="SHOW_AVAILABLE_HOSTELS"/>
                <input type="hidden" name="number" value="1"/>
                <label>
                    <select name="type">
                        <option value="booking">${booking}</option>
                        <option value="payment">${payment}</option>
                    </select>
                </label>
                <label>
                    <select name="city">
                        <c:forEach items="${requestScope.cities}" var="city">
                            <option value="${city.key}">${city.value}</option>
                        </c:forEach>
                    </select>
                </label>
                <input type="number" name="rooms" placeholder="${rooms}" value="${sessionScope.username}"/>
                <input type="number" name="days" placeholder="${days}" value="${sessionScope.name}"/>
                <label>
                    <input type="date" name="date"/>
                </label>
                <input type="submit" value="${search}"/>
            </form>
        </div>
    </section>
</div>


<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>