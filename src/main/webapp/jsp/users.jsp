<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ahs" uri="hostelTag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.users" var="users"/>
    <fmt:message bundle="${loc}" key="locale.table.title.id" var="id"/>
    <fmt:message bundle="${loc}" key="locale.table.title.username" var="username"/>
    <fmt:message bundle="${loc}" key="locale.table.title.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.table.title.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.table.title.discount" var="discount"/>
    <fmt:message bundle="${loc}" key="locale.table.title.status" var="status"/>
    <fmt:message bundle="${loc}" key="locale.table.title.reason" var="reason"/>
    <fmt:message bundle="${loc}" key="locale.table.title.block.date" var="blockDate"/>
    <fmt:message bundle="${loc}" key="locale.table.title.action" var="action"/>
    <fmt:message bundle="${loc}" key="locale.table.title.requests" var="requests"/>
    <fmt:message bundle="${loc}" key="locale.button.block" var="block"/>
    <fmt:message bundle="${loc}" key="locale.button.unlock" var="unlock"/>
    <title> ${users} | ${sessionScope.user.personalInfo.username} | Hostel System</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<c:if test="${fn:length(requestScope.users) gt 0}">
    <div class="full-table-container">
        <table>
            <tr>
                <th>${id}</th>
                <th>${username}</th>
                <th>${email}</th>
                <th>${name}</th>
                <th>${discount}</th>
                <th>${status}</th>
                <th>${reason}</th>
                <th>${blockDate}</th>
                <th>${requests}</th>
                <th>${action}</th>
            </tr>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td data-label="${id}"><c:out value="${user.id}"/></td>
                    <td data-label="${username}"><c:out value="${user.personalInfo.username}"/></td>
                    <td data-label="${email}"><c:out value="${user.personalInfo.email}"/></td>
                    <td data-label="${name}"><ahs:full-name name="${user.personalInfo.name}" lastName="${user.personalInfo.lastName}" surname="${user.personalInfo.surname}"/></td>
                    <td data-label="${discount}">
                        <c:choose>
                            <c:when test="${user.discount ge 50}">
                                <ahs:minus id="${user.id}" page="${requestScope.page.current}"/>${user.discount}
                            </c:when>
                            <c:when test="${user.discount le 0}">
                                ${user.discount}<ahs:plus id="${user.id}" page="${requestScope.page.current}"/>
                            </c:when>
                            <c:otherwise>
                                <ahs:minus id="${user.id}" page="${requestScope.page.current}"/>${user.discount}<ahs:plus id="${user.id}" page="${requestScope.page.current}"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td data-label="${status}"><ahs:user-status userStatus="${user.status}"/></td>
                    <td data-label="${reason}"><c:out value="${user.blockInfo.reason}"/></td>
                    <td data-label="${blockDate}"><c:out value="${user.blockInfo.date}"/></td>
                    <td data-label="${requests}"><c:out value="${user.requests}"/></td>
                    <td data-label="${action}">
                        <c:choose>
                            <c:when test = "${user.status eq 'BANNED'}">
                                <form class="unlock" action="${pageContext.request.contextPath}/hostel_system" method="get">
                                    <input type="hidden" name="command" value="UNLOCK"/>
                                    <input type="hidden" name="number" value="${requestScope.page.current}"/>
                                    <input type="hidden" name="id" value="${user.id}"/>
                                    <input type="submit" value="${unlock}"/>
                                </form>
                            </c:when>
                            <c:when test = "${user.status eq 'USER'}">
                                <form class="block" action="${pageContext.request.contextPath}/hostel_system" method="get">
                                    <input type="hidden" name="command" value="BLOCK"/>
                                    <input type="hidden" name="number" value="${requestScope.page.current}"/>
                                    <input type="hidden" name="id" value="${user.id}"/>
                                    <label>
                                        <select name="reason">
                                            <c:forEach items="${requestScope.reasons}" var="reason">
                                                <option value="${reason.key}">${reason.value}</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                    <input type="submit" value="${block}"/>
                                </form>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
    </div>
</c:if>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
<jsp:include page="/WEB-INF/jsp/topButton.jsp"/>
</body>
</html>
