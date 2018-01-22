<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>
<html>
<head>
    <title>User Info</title>
</head>
<link rel="stylesheet" href="assets/css/carousel.css">
<link rel="stylesheet" href="assets/css/input_form.css">
<link rel="stylesheet" href="assets/css/navigation_bar.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="shortcut icon" href="assets/images/favicon.png">
<body>
<c:choose>
    <c:when test = "${sessionScope.user == null}">
        <jsp:forward page="index.jsp" />
    </c:when>
</c:choose>
<div class="container">

</div>
<div style="padding:20px;"></div>
<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <c:choose>
                <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                    <h3>Welcome back, <c:out value="${sessionScope.user.name}"/>!</h3>
                    Your personal discount: <c:out value="${sessionScope.user.discount}"/>%
                    <hr>
                    <c:choose>
                        <c:when test = "${sessionScope.user.status.toString() eq 'admin'}">
                            <form action="Controller" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_USERS"/>
                                <input type="submit" value="Show Users"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_HOSTELS"/>
                                <input type="submit" value="Show Hostels"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_REQUESTS"/>
                                <input type="submit" value="Show Requests"/>
                            </form>
                        </c:when>
                        <c:when test = "${sessionScope.user.status.toString() eq 'user'}">
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="SHOW_CREATING_FORM"/>
                                <input type="submit" value="Add Request"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="SHOW_PREFERENCES"/>
                                <input type="submit" value="Change Data"/>
                            </form>
                            <form action="WEB-INF/jsp/preferences.jsp">
                                <input type="submit" value="Change Data"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_USER_REQUESTS"/>
                                <input type="submit" value="Show Requests"/>
                            </form>
                        </c:when>
                    </c:choose>

                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="LOGOUT"/>
                        <input type="submit" value="LogOut"/>
                    </form>
                </c:when>
                <c:otherwise>
                    <h3>Добро пожаловать, <c:out value="${sessionScope.user.name}"/>!</h3>
                    Ваша персональная скидка: <c:out value="${sessionScope.user.discount}"/>%
                    <hr>
                    <c:choose>
                        <c:when test = "${sessionScope.user.status.toString() eq 'admin'}">
                            <form action="Controller" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_USERS"/>
                                <input type="submit" value="Просмотреть пользователей"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_HOSTELS"/>
                                <input type="submit" value="Просмотр хостелов"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_REQUESTS"/>
                                <input type="submit" value="Просмотр заявок"/>
                            </form>
                        </c:when>
                        <c:when test = "${sessionScope.user.status.toString() eq 'user'}">
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="SHOW_CREATING_FORM"/>
                                <input type="submit" value="Создать заявку"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="SHOW_PREFERENCES"/>
                                <input type="submit" value="Изменить данные"/>
                            </form>
                            <form action="WEB-INF/jsp/preferences.jsp">
                                <input type="submit" value="Изменить данные"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="number" value="1"/>
                                <input type="hidden" name="command" value="SHOW_USER_REQUESTS"/>
                                <input type="submit" value="Просмотр заявок"/>
                            </form>
                        </c:when>
                    </c:choose>

                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="LOGOUT"/>
                        <input type="submit" value="Выход"/>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </section>
</div>

<ul id="navigation_bar">
    <c:choose>
        <c:when test = "${sessionScope.lang.toString() eq 'en'}">
            <li><a href="Controller?command=LOGOUT">Exit</a></li>
            <li><a href="#">Language</a>
                <ul>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=ru&page=home.jsp">Russian</a></li>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=en&page=home.jsp">English</a></li>
                </ul>
            </li>
            <li class="active"><a href="home.jsp">Home</a></li>
            <li class="system-name"><a>Hostel System</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="Controller?command=LOGOUT">Выйти</a></li>
            <li><a href="#">Язык</a>
                <ul>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=ru&page=home.jsp">Русский</a></li>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=en&page=home.jsp">Английский</a></li>
                </ul>
            </li>
            <li class="active"><a href="home.jsp">Домой</a></li>
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