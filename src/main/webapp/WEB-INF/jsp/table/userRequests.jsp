<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ahs" uri="hostelTag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.table.title.request.id" var="requestId"/>
    <fmt:message bundle="${loc}" key="locale.table.title.hostel.name" var="hostelInfo"/>
    <fmt:message bundle="${loc}" key="locale.table.title.type" var="type"/>
    <fmt:message bundle="${loc}" key="locale.table.title.rooms.count" var="rooms"/>
    <fmt:message bundle="${loc}" key="locale.table.title.days.count" var="days"/>
    <fmt:message bundle="${loc}" key="locale.table.title.cost" var="cost"/>
    <fmt:message bundle="${loc}" key="locale.table.title.date" var="date"/>
    <fmt:message bundle="${loc}" key="locale.table.title.status" var="status"/>
    <fmt:message bundle="${loc}" key="locale.table.title.action" var="action"/>
</head>
<body>
<c:if test="${fn:length(requestScope.requests) gt 0}">
    <div class="full-table-container">
        <table>
            <tr>
                <th>${requestId}</th>
                <th>${hostelInfo}</th>
                <th>${type}</th>
                <th>${rooms}</th>
                <th>${days}</th>
                <th>${cost}</th>
                <th>${date}</th>
                <th>${action}</th>
            </tr>
            <c:forEach items="${requestScope.requests}" var="request">
                <tr class="${request.status}">
                    <td data-label="${requestId}"><c:out value="${request.id}"/></td>
                    <td data-label="${hostelInfo}"><c:out value="${request.hostelInfo}"/></td>
                    <td data-label="${type}"><ahs:booking-type bookingType="${request.type}"/></td>
                    <td data-label="${rooms}"><c:out value="${request.room}"/></td>
                    <td data-label="${days}"><c:out value="${request.days}"/></td>
                    <td data-label="${cost}"><c:out value="${request.cost}"/></td>
                    <td data-label="${date}"><c:out value="${request.date}"/></td>
                    <td data-label="${action}">
                        <c:set var = "currentPage" scope="page" value = ""/>
                        <c:if test = "${request.status eq 'PROCESSING'}">
                            <jsp:include page="/WEB-INF/jsp/button/requestsUser.jsp">
                                <jsp:param name="nextCommand" value="${param.command}" />
                                <jsp:param name="page" value="${requestScope.page.current}" />
                                <jsp:param name="userId" value="${request.userId}" />
                                <jsp:param name="requestId" value="${request.id}" />
                            </jsp:include>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
    </div>
</c:if>
</body>
</html>