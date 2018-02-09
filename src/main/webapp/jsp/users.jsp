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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/topButton.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/backToTop.js"></script>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.users" var="users"/>
    <fmt:message bundle="${loc}" key="locale.table.title.user.id" var="id"/>
    <fmt:message bundle="${loc}" key="locale.table.title.username" var="username"/>
    <fmt:message bundle="${loc}" key="locale.table.title.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.table.title.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.table.title.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="locale.table.title.lastname" var="lastname"/>
    <fmt:message bundle="${loc}" key="locale.table.title.discount" var="discount"/>
    <fmt:message bundle="${loc}" key="locale.table.title.status" var="status"/>
    <fmt:message bundle="${loc}" key="locale.table.title.reason" var="reason"/>
    <fmt:message bundle="${loc}" key="locale.table.title.block.date" var="blockDate"/>
    <fmt:message bundle="${loc}" key="locale.table.title.action" var="action"/>
    <fmt:message bundle="${loc}" key="locale.table.title.requests" var="requests"/>
    <fmt:message bundle="${loc}" key="locale.button.block" var="block"/>
    <fmt:message bundle="${loc}" key="locale.button.unlock" var="unlock"/>
    <fmt:message bundle="${loc}" key="locale.button.top" var="toTop"/>
    <title> ${users} | ${sessionScope.user.personalInfo.username} | Hostel System</title>
</head>
<body>

<div style="padding:20px;"></div>

<c:if test="${fn:length(requestScope.users) gt 0}">
    <div class="table-container">
        <table>
            <tr>
                <th>${id}</th>
                <th>${username}</th>
                <th>${email}</th>
                <th>${name}</th>
                <th>${surname}</th>
                <th>${lastname}</th>
                <th>${discount}</th>
                <th>${status}</th>
                <th>${reason}</th>
                <th>${blockDate}</th>
                <th>${requests}</th>
                <th>${action}</th>
            </tr>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.personalInfo.username}"/></td>
                    <td><c:out value="${user.personalInfo.email}"/></td>
                    <td><c:out value="${user.personalInfo.name}"/></td>
                    <td><c:out value="${user.personalInfo.surname}"/></td>
                    <td><c:out value="${user.personalInfo.lastname}"/></td>
                    <td>
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
                    <td><ahs:user-status userStatus="${user.status}"/></td>
                    <td><c:out value="${user.blockInfo.blockReason}"/></td>
                    <td><c:out value="${user.blockInfo.blockDate}"/></td>
                    <td><c:out value="${user.requests}"/></td>
                    <td>
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
    </div>
    <jsp:include page="/WEB-INF/jsp/pagination.jsp"/>
</c:if>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
<div id="toTop">^ ${toTop}</div>
</body>
</html>
