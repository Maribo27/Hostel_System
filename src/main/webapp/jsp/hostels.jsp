<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ahs" uri="hostelTag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Hostels</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/topButton.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/raleway_font.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/backToTop.js"></script>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.hostels" var="hostels"/>
    <fmt:message bundle="${loc}" key="locale.table.title.hostel.id" var="id"/>
    <fmt:message bundle="${loc}" key="locale.table.title.hostel.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.table.title.country" var="country"/>
    <fmt:message bundle="${loc}" key="locale.table.title.city" var="city"/>
    <fmt:message bundle="${loc}" key="locale.table.title.address" var="address"/>
    <fmt:message bundle="${loc}" key="locale.table.title.booking" var="booking"/>
    <fmt:message bundle="${loc}" key="locale.table.title.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.table.title.rooms" var="count"/>
    <fmt:message bundle="${loc}" key="locale.table.title.cost" var="cost"/>
    <fmt:message bundle="${loc}" key="locale.table.title.action" var="action"/>
    <fmt:message bundle="${loc}" key="locale.button.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="locale.button.add.request" var="request"/>
    <fmt:message bundle="${loc}" key="locale.button.top" var="toTop"/>
    <title> ${hostels} | ${sessionScope.user.personalInfo.username} | Hostel System</title>
</head>
<body>

<div style="padding:20px;"></div>
<jsp:include page="/WEB-INF/jsp/chooseParameters.jsp"/>
<c:if test="${fn:length(requestScope.hostels) gt 0}">
    <div class="table-container">
        <table>
            <tr>
                <th>${name}</th>
                <th>${city}</th>
                <th>${address}</th>
                <th>${booking}</th>
                <th>${email}</th>
                <th>${cost}</th>
                <th>${action}</th>
            </tr>
            <c:forEach items="${requestScope.hostels}" var="hostel">
                <c:set var="_cost" scope="request" value="${hostel.cost}"/>
                <c:set var="_rooms" scope="request" value="${requestScope.rooms}"/>
                <c:set var="_days" scope="request" value="${requestScope.days}"/>
                <c:set var="_discount" scope="request" value="${sessionScope.user.discount}"/>
                <c:set var="balance" scope="request" value="${sessionScope.user.balance}"/>
                <tr>
                    <td><c:out value="${hostel.name}"/></td>
                    <td><c:out value="${hostel.address.city}"/></td>
                    <td><c:out value="${hostel.address.address}"/></td>
                    <td><ahs:booking-type bookingType="${hostel.booking}"/></td>
                    <td><c:out value="${hostel.email}"/></td>
                    <td><ahs:price cost="${_cost}" discount="${_discount}" days="${_days}" rooms="${_rooms}"/></td>
                    <td>
                        <c:set var="price" scope="request" value="${_cost * _days * _rooms * (1 - _discount / 100)}"/>
                        <c:if test="${price < balance}">
                            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                                <input type="hidden" name="command" value="ADD_REQUEST"/>
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="type" value="${requestScope.type}"/>
                                <input type="hidden" name="cost" value="${_cost}"/>
                                <input type="hidden" name="rooms" value="${_rooms}"/>
                                <input type="hidden" name="days" value="${_days}"/>
                                <input type="hidden" name="date" value="${requestScope.date}"/>
                                <input type="hidden" name="hostel" value="${hostel.id}"/>
                                <input type="submit" value="${request}"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
<div id="toTop">^ ${toTop}</div>
</body>
</html>