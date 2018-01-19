<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Block user</title>
</head>
<link rel="stylesheet" href="../../assets/css/carousel.css">
<link rel="stylesheet" href="../../assets/css/input_form.css">
<link rel="stylesheet" href="../../assets/css/navigation_bar.css">
<link rel="stylesheet" href="../../assets/css/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="shortcut icon" href="../../assets/images/favicon.png">

<body>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <c:set var = "ret" scope = "session" value = "Назад"/>
            <c:set var = "block" scope = "session" value = "Блокировать"/>
            <c:if test = "${sessionScope.lang.toString() eq 'en'}">
                <c:set var = "ret" scope = "session" value = "Return"/>
                <c:set var = "block" scope = "session" value = "Block User"/>
            </c:if>
            <form action="Controller" method="get">
                <input type="hidden" name="command" value="BLOCK"/>
                <input type="hidden" name="id" value="${requestScope.id}"/>
                <select name="reason">
                    <c:forEach items="${requestScope.reasons}" var="reason">
                        <option value="${reason.key}">${reason.value}</option>
                    </c:forEach>
                </select>
                <input type="date" name="date"/>
                <input id="appt-time" type="time" name="time" step="2">
                <input type="submit" value="${block}"/>
            </form>
            <form action="Controller" method="get">
                <input type="hidden" name="command" value="SHOW_USERS"/>
                <input type="submit" value="${ret}"/>
            </form>
        </div>
    </section>
</div>

<ul id="navigation_bar">
    <c:choose>
        <c:when test = "${sessionScope.lang.toString() eq 'en'}">
            <li><a href="Controller?command=LOGOUT">Exit</a></li>
            <li><a href="#">Language</a>
                <ul>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=ru&page=/WEB-INF/jsp/blockUser.jsp">Russian</a></li>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=en&page=/WEB-INF/jsp/blockUser.jsp">English</a></li>
                </ul>
            </li>
            <li class="active"><a href="../../home.jsp">Home</a></li>
            <li class="system-name"><a>Hostel System</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="Controller?command=LOGOUT">Выйти</a></li>
            <li><a href="#">Язык</a>
                <ul>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=ru&page=/WEB-INF/jsp/blockUser.jsp">Русский</a></li>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=en&page=/WEB-INF/jsp/blockUser.jsp">Английский</a></li>
                </ul>
            </li>
            <li class="active"><a href="../../home.jsp">Домой</a></li>
            <li class="system-name"><a>Поиск хостелов</a></li>
        </c:otherwise>
    </c:choose>
</ul>

<footer class="footer">
    <div class="footer-bottom">
        <div class="container" align="center">Copyright © 2017-2018 HostelSystem. Design by Maryia Bahumilchyk</div>
    </div>
</footer>
</body>
</html>