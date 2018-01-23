<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <c:set var = "currentPage" scope = "session" value = "jsp/requestsInfoPage.jsp"/>
    <link rel="stylesheet" href="../assets/css/carousel.css">
    <link rel="stylesheet" href="../assets/css/input_form.css">
    <link rel="stylesheet" href="../assets/css/navigation_bar.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="../assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.requests" var="requests"/>
    <fmt:message bundle="${loc}" key="locale.table.title.request.id" var="requestId"/>
    <fmt:message bundle="${loc}" key="locale.table.title.user.id" var="userId"/>
    <fmt:message bundle="${loc}" key="locale.table.title.hostel.id" var="hostelId"/>
    <fmt:message bundle="${loc}" key="locale.table.title.type" var="type"/>
    <fmt:message bundle="${loc}" key="locale.table.title.rooms.count" var="rooms"/>
    <fmt:message bundle="${loc}" key="locale.table.title.days.count" var="days"/>
    <fmt:message bundle="${loc}" key="locale.table.title.cost" var="cost"/>
    <fmt:message bundle="${loc}" key="locale.table.title.date" var="date"/>
    <fmt:message bundle="${loc}" key="locale.table.title.status" var="status"/>
    <fmt:message bundle="${loc}" key="locale.table.title.action" var="action"/>
    <fmt:message bundle="${loc}" key="locale.button.approve" var="approve"/>
    <fmt:message bundle="${loc}" key="locale.button.deny" var="deny"/>
    <fmt:message bundle="${loc}" key="locale.button.cancel" var="cancel"/>
    <title> ${requests} | ${sessionScope.user.username} | Hostel System</title>
</head>

<body>
<div style="padding:20px;"></div>
<div class="table-container">
    <table>
        <tr>
            <th>${requestId}</th>
            <th>${userId}</th>
            <th>${hostelId}</th>
            <th>${type}</th>
            <th>${rooms}</th>
            <th>${days}</th>
            <th>${cost}</th>
            <th>${date}</th>
            <th>${status}</th>
            <th>${action}</th>
        </tr>
        <c:forEach items="${requestScope.requests}" var="request">
            <tr>
                <td><c:out value="${request.id}"/></td>
                <td><c:out value="${request.userId}"/></td>
                <td><c:out value="${request.hostelId}"/></td>
                <td><c:out value="${request.type}"/></td>
                <td><c:out value="${request.room}"/></td>
                <td><c:out value="${request.days}"/></td>
                <td><c:out value="${request.cost}"/></td>
                <td><c:out value="${request.date}"/></td>
                <td><c:out value="${request.status}"/></td>
                <c:choose>
                    <c:when test = "${sessionScope.user.status.toString() eq 'admin'}">
                        <td>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="CHANGE_REQUEST_STATUS"/>
                                <input type="hidden" name="request" value="${request.id}"/>
                                <input type="hidden" name="status" value="approve"/>
                                <input type="submit" value="${approve}"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="CHANGE_REQUEST_STATUS"/>
                                <input type="hidden" name="request" value="${request.id}"/>
                                <input type="hidden" name="status" value="denied"/>
                                <input type="submit" value="${deny}"/>
                            </form>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="CHANGE_REQUEST_STATUS"/>
                                <input type="hidden" name="request" value="${request.id}"/>
                                <input type="hidden" name="status" value="denied"/>
                                <input type="submit" value="${cancel}"/>
                            </form>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>

<jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>