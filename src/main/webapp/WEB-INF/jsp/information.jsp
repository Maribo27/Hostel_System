<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>

    <fmt:message bundle="${loc}" key="locale.home.page.message.booking" var="booking"/>
    <fmt:message bundle="${loc}" key="locale.home.page.message.booking.description" var="bookingDescription"/>
    <fmt:message bundle="${loc}" key="locale.home.page.message.payment" var="payment"/>
    <fmt:message bundle="${loc}" key="locale.home.page.message.payment.description" var="paymentDescription"/>
    <fmt:message bundle="${loc}" key="locale.home.page.message.discount" var="discount"/>
    <fmt:message bundle="${loc}" key="locale.home.page.message.discount.description" var="discountDescription"/>
</head>
<body>
<div class="system-information">
    <h2>${booking}</h2>
    <p>${bookingDescription}</p>
    <hr>
    <h2>${payment}</h2>
    <p>${paymentDescription}</p>
    <hr>
    <h2>${discount}</h2>
    <p>${discountDescription}</p>
</div>
</body>
</html>


