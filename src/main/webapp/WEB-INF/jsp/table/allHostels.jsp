<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ahs" uri="hostelTag" %>
<html>
<head>
    <title>Hostels</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.table.title.hostel.id" var="id"/>
    <fmt:message bundle="${loc}" key="locale.table.title.hostel.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.table.title.country" var="country"/>
    <fmt:message bundle="${loc}" key="locale.table.title.city" var="city"/>
    <fmt:message bundle="${loc}" key="locale.table.title.address" var="address"/>
    <fmt:message bundle="${loc}" key="locale.table.title.booking" var="booking"/>
    <fmt:message bundle="${loc}" key="locale.table.title.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.table.title.room.cost" var="cost"/>
</head>
<body>
<div class="full-table-container">
    <table>
        <tr>
            <th>${id}</th>
            <th>${name}</th>
            <th>${country}</th>
            <th>${city}</th>
            <th>${address}</th>
            <th>${booking}</th>
            <th>${email}</th>
            <th>${cost}</th>
        </tr>
        <c:forEach items="${requestScope.hostels}" var="hostel">
            <tr>
                <td data-label="${id}"><c:out value="${hostel.id}"/></td>
                <td data-label="${name}"><c:out value="${hostel.name}"/></td>
                <td data-label="${country}"><c:out value="${hostel.fullAddress.country}"/></td>
                <td data-label="${city}"><c:out value="${hostel.fullAddress.city}"/></td>
                <td data-label="${address}"><c:out value="${hostel.fullAddress.address}"/></td>
                <td data-label="${booking}"><ahs:booking-type bookingType="${hostel.booking}"/></td>
                <td data-label="${email}"><c:out value="${hostel.email}"/></td>
                <td data-label="${cost}"><c:out value="${hostel.cost}"/></td>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
</div>
</body>
</html>