<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <c:set var = "currentPage" scope = "session" value = "WEB-INF/jsp/chooseParameters.jsp"/>
    <link rel="stylesheet" href="../../assets/css/carousel.css">
    <link rel="stylesheet" href="../../assets/css/input_form.css">
    <link rel="stylesheet" href="../../assets/css/navigation_bar.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="../../assets/images/favicon.png">
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
    <title> ${bookingPage} | ${sessionScope.user.username} | Hostel System</title>
</head>

<body>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="command" value="CREATE_REQUEST"/>
                <select name="type">
                    <option value="booking">${booking}</option>
                    <option value="payment">${payment}</option>
                </select>
                <select name="city">
                    <c:forEach items="${requestScope.cities}" var="city">
                        <option value="${city.key}">${city.value}</option>
                    </c:forEach>
                </select>
                <input type="number" name="rooms" placeholder="${rooms}" pattern="^[\w]{5,20}$" value="${sessionScope.username}"/>
                <input type="number" name="days" placeholder="${days}" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.name}"/>
                <input type="date" name="date"/>
                <input type="submit" value="${search}"/>
            </form>
        </div>
    </section>
</div>


<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>