<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ahs" uri="hostelTag" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Hostels</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.table.title.hostel.name" var="name"/>
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
</head>
<body>
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
                    <td data-label="${name}"><c:out value="${hostel.name}"/></td>
                    <td data-label="${city}"><c:out value="${hostel.fullAddress.city}"/></td>
                    <td data-label="${address}"><c:out value="${hostel.fullAddress.address}"/></td>
                    <td data-label="${booking}"><ahs:booking-type bookingType="${hostel.booking}"/></td>
                    <td data-label="${email}"><c:out value="${hostel.email}"/></td>
                    <td data-label="${cost}"><c:set var="price" scope="request"/>
                        <ahs:price cost="${_cost}" discount="${_discount}" days="${_days}" rooms="${_rooms}"/></td>
                    <td data-label="${action}">
                        <c:choose>
                            <c:when test="${price < balance && requestScope.type eq 'payment'}">
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
                            </c:when>
                            <c:when test="${requestScope.type eq 'booking'}">
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
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
    </div>
</c:if>
</body>
</html>