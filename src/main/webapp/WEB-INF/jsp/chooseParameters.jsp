<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form parameters.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/chosen.css">
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.booking" var="bookingPage"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.rooms" var="rooms"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.days" var="days"/>
    <fmt:message bundle="${loc}" key="locale.button.return" var="return"/>
    <fmt:message bundle="${loc}" key="locale.button.block" var="block"/>
    <fmt:message bundle="${loc}" key="locale.button.search" var="search"/>
    <fmt:message bundle="${loc}" key="locale.list.booking" var="booking"/>
    <fmt:message bundle="${loc}" key="locale.list.payment" var="payment"/>
    <fmt:message bundle="${loc}" key="locale.message.choose.city" var="chooseCity"/>
    <fmt:message bundle="${loc}" key="locale.table.title.type" var="type"/>
    <fmt:message bundle="${loc}" key="locale.table.title.city" var="city"/>
    <fmt:message bundle="${loc}" key="locale.table.title.rooms" var="roomsNumber"/>
    <fmt:message bundle="${loc}" key="locale.table.title.days.count" var="daysNumber"/>
    <fmt:message bundle="${loc}" key="locale.table.title.date" var="date"/>
</head>

<body>
<c:choose>
    <c:when test="${not empty requestScope.hostels}">
        <div id="sidebar">
            <section class="container">
                <div class="input-data-form">
                    <form action="${pageContext.request.contextPath}/hostel_system" method="post">
                        <input type="hidden" name="command" value="SHOW_AVAILABLE_HOSTELS"/>
                        <input type="hidden" name="number" value="1"/>
                        <div class="selection-form">
                            <label>${type}</label>
                            <select name="type" data-placeholder="${booking}" class="chosen-select" tabindex="1">
                                <option value="booking">${booking}</option>
                                <option value="payment">${payment}</option>
                            </select>
                        </div>
                        <div class="selection-form">
                            <label>${city}</label>
                            <select name="city" data-placeholder="${chooseCity}" class="chosen-select" tabindex="2">
                                <c:forEach items="${requestScope.cities}" var="city">
                                    <option value="${city.key}">${city.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <article>
                            <label>${roomsNumber}</label>
                            <input type="number" name="rooms" placeholder="${rooms}" value="${requestScope.rooms}" min="1"/>
                            <label>${daysNumber}</label>
                            <input type="number" name="days" placeholder="${days}" value="${requestScope.days}" min="1"/>
                            <label>${date}</label>
                            <input type="date" name="date" value="${requestScope.date}"/>
                        </article>
                        <input type="submit" value="${search}"/>
                        <script src="${pageContext.request.contextPath}/assets/js/support/jquery-3.2.1.min.js" type="text/javascript"></script>
                        <script src="${pageContext.request.contextPath}/assets/js/support/chosen.jquery.js" type="text/javascript"></script>
                        <script src="${pageContext.request.contextPath}/assets/js/support/init.js" type="text/javascript" charset="utf-8"></script>
                    </form>
                    <h2>${requestScope.error}</h2>
                </div>
            </section>
        </div>
    </c:when>
    <c:otherwise>
        <div style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; display: flex; align-items: center; align-content: center; justify-content: center; overflow: auto;  ">
            <div class="parameters-form">
                <form action="${pageContext.request.contextPath}/hostel_system" method="post">
                    <input type="hidden" name="command" value="SHOW_AVAILABLE_HOSTELS"/>
                    <input type="hidden" name="number" value="1"/>
                    <div class="selection-form">
                        <label>${type}</label>
                        <select name="type" data-placeholder="${booking}" class="chosen-select" tabindex="1">
                            <option value="booking">${booking}</option>
                            <option value="payment">${payment}</option>
                        </select>
                    </div>
                    <div class="selection-form">
                        <label>${city}</label>
                        <select name="city" data-placeholder="${chooseCity}" class="chosen-select" tabindex="2">
                            <c:forEach items="${requestScope.cities}" var="city">
                                <option value="${city.key}">${city.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <article>
                        <label>${rooms}</label>
                        <input type="number" name="rooms" placeholder="${rooms}" value="${requestScope.rooms}" min="1"/>
                        <label>${days}</label>
                        <input type="number" name="days" placeholder="${days}" value="${requestScope.days}" min="1"/>
                        <label>${date}</label>
                        <input type="date" name="date" value="${requestScope.date}"/>
                    </article>
                    <input type="submit" value="${search}"/>
                    <script src="${pageContext.request.contextPath}/assets/js/support/jquery-3.2.1.min.js" type="text/javascript"></script>
                    <script src="${pageContext.request.contextPath}/assets/js/support/chosen.jquery.js" type="text/javascript"></script>
                    <script src="${pageContext.request.contextPath}/assets/js/support/init.js" type="text/javascript" charset="utf-8"></script>
                </form>
                <h2>${requestScope.error}</h2>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>