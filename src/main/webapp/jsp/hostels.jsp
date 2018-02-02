<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ahs" uri="hostelTag" %>
<html>
<head>
    <title>Hostels</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
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
    <fmt:message bundle="${loc}" key="locale.table.title.room.cost" var="cost"/>
    <fmt:message bundle="${loc}" key="locale.table.title.action" var="action"/>
    <fmt:message bundle="${loc}" key="locale.button.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="locale.button.add.request" var="request"/>
    <title> ${hostels} | ${sessionScope.user.username} | Hostel System</title>
</head>
<body>

<div style="padding:20px;"></div>
<div class="table-container">
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
            <c:if test = "${sessionScope.user.status eq 'USER'}">
                <th>${action}</th>
            </c:if>
        </tr>
        <c:forEach items="${requestScope.hostels}" var="hostel">
            <tr>
                <td><c:out value="${hostel.id}"/></td>
                <td><c:out value="${hostel.name}"/></td>
                <td><c:out value="${hostel.country}"/></td>
                <td><c:out value="${hostel.city}"/></td>
                <td><c:out value="${hostel.address}"/></td>
                <td><ahs:booking-type bookingType="${hostel.booking}"/></td>
                <td><c:out value="${hostel.email}"/></td>
                <td><c:out value="${hostel.cost}"/></td>
                <td>
                    <c:if test = "${sessionScope.user.status eq 'USER'}">
                        <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                            <input type="hidden" name="command" value="ADD_REQUEST"/>
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
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>