<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <c:set var = "currentPage" scope = "session" value = "WEB-INF/jsp/blockUser.jsp"/>
    <link rel="stylesheet" href="../../assets/css/carousel.css">
    <link rel="stylesheet" href="../../assets/css/input_form.css">
    <link rel="stylesheet" href="../../assets/css/navigation_bar.css">
    <link rel="stylesheet" href="../../assets/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="shortcut icon" href="../../assets/images/favicon.png">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.block" var="blockPage"/>
    <fmt:message bundle="${loc}" key="locale.button.return" var="return"/>
    <fmt:message bundle="${loc}" key="locale.button.block" var="block"/>
    <title> ${blockPage} | ${sessionScope.user.username} | Hostel System</title>
</head>

<body>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="command" value="BLOCK"/>
                <input type="hidden" name="id" value="${requestScope.id}"/>
                <select name="reason">
                    <c:forEach items="${requestScope.reasons}" var="reason">
                        <option value="${reason.key}">${reason.value}</option>
                    </c:forEach>
                </select>
                <input type="date" name="date"/>
                <input type="submit" value="${block}"/>
            </form>
            <form action="${pageContext.request.contextPath}/hostel_system" method="get">
                <input type="hidden" name="command" value="SHOW_USERS"/>
                <input type="submit" value="${return}"/>
            </form>
        </div>
    </section>
</div>


<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>