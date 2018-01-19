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
            <c:set var = "return" scope = "session" value = "Назад"/>
            <c:set var = "block" scope = "session" value = "Блокировать"/>
            <c:if test = "${sessionScope.lang.toString() eq 'en'}">
                <c:set var = "return" scope = "session" value = "Return"/>
                <c:set var = "block" scope = "session" value = "Block User"/>
            </c:if>
            <form action="Controller" method="get">
                <input type="hidden" name="command" value="CREATE_REQUEST"/>
                <c:choose>
                    <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                        <select name="type">
                            <option value="booking">Booking</option>
                            <option value="payment">Payment</option>
                        </select>
                        <select name="city">
                            <c:forEach items="${requestScope.cities}" var="city">
                                <option value="${city.key}">${city.value}</option>
                            </c:forEach>
                        </select>
                        <input type="number" name="rooms" placeholder="Enter number of rooms" pattern="^[\w]{5,20}$" value="${sessionScope.username}"/>
                        <input type="number" name="days" placeholder="Enter number of days" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.name}"/>
                        <input type="date" name="date"/>
                        <input type="submit" value="Search"/>
                    </c:when>
                    <c:otherwise>
                        <select name="type">
                            <option value="booking">Забронировать</option>
                            <option value="payment">Моментальная оплата</option>
                        </select>
                        <select name="city">
                            <c:forEach items="${requestScope.cities}" var="city">
                                <option value="${city.key}">${city.value}</option>
                            </c:forEach>
                        </select>
                        <input type="number" name="rooms" placeholder="Введите количество номеров" pattern="^[\w]{5,20}$" value="${sessionScope.username}"/>
                        <input type="number" name="days" placeholder="Введите количество дней" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.name}"/>
                        <input type="date" name="date"/>
                        <input type="submit" value="Поиск"/>
                    </c:otherwise>
                </c:choose>
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
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=ru&page=home.jsp">Russian</a></li>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=en&page=home.jsp">English</a></li>
                </ul>
            </li>
            <li class="active"><a href="../../home.jsp">Home</a></li>
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