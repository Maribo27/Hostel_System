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
    <fmt:message bundle="${loc}" key="locale.table.title.room.cost" var="cost"/>
    <fmt:message bundle="${loc}" key="locale.table.title.action" var="action"/>
    <fmt:message bundle="${loc}" key="locale.button.delete" var="delete"/>
    <fmt:message bundle="${loc}" key="locale.button.add.request" var="request"/>
    <fmt:message bundle="${loc}" key="locale.button.top" var="toTop"/>
    <title> ${hostels} | ${sessionScope.user.personalInfo.username} | Hostel System</title>
</head>
<body>

<div style="padding:20px;"></div>
<c:if test="${fn:length(requestScope.hostels) gt 0}">
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
            </tr>
            <c:forEach items="${requestScope.hostels}" var="hostel">
                <tr>
                    <td><c:out value="${hostel.id}"/></td>
                    <td><c:out value="${hostel.name}"/></td>
                    <td><c:out value="${hostel.address.country}"/></td>
                    <td><c:out value="${hostel.address.city}"/></td>
                    <td><c:out value="${hostel.address.address}"/></td>
                    <td><ahs:booking-type bookingType="${hostel.booking}"/></td>
                    <td><c:out value="${hostel.email}"/></td>
                    <td><c:out value="${hostel.cost}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>