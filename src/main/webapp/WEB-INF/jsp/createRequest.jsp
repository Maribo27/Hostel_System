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
            <h3><c:out value="${requestScope.type}"/></h3>
            <h3><c:out value="${requestScope.city}"/></h3>
            <h3><c:out value="${requestScope.rooms}"/></h3>
            <h3><c:out value="${requestScope.days}"/></h3>
            <h3><c:out value="${requestScope.date}"/></h3>
        </div>
    </section>
</div>

<div class="table-container">
    <table>
        <tr>
            <c:choose>
                <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Country</th>
                    <th>City</th>
                    <th>Address</th>
                    <th>Booking</th>
                    <th>Email</th>
                    <th>Room</th>
                    <th>Cost</th>
                    <th>Action</th>
                </c:when>
                <c:otherwise>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Страна</th>
                    <th>Город</th>
                    <th>Адрес</th>
                    <th>Возможность бронирования</th>
                    <th>Email</th>
                    <th>Количество свободных номеров</th>
                    <th>Стоимость номера</th>
                    <th>Действие</th>
                </c:otherwise>
            </c:choose>
        </tr>
        <c:forEach items="${requestScope.hostels}" var="hostel" begin="${requestScope.begin}" end="${requestScope.end}">
            <tr>
                <td><c:out value="${hostel.id}"/></td>
                <td><c:out value="${hostel.name}"/></td>
                <td><c:out value="${hostel.country}"/></td>
                <td><c:out value="${hostel.city}"/></td>
                <td><c:out value="${hostel.address}"/></td>
                <td><c:out value="${hostel.booking}"/></td>
                <td><c:out value="${hostel.email}"/></td>
                <td><c:out value="${hostel.room}"/></td>
                <td><c:out value="${hostel.cost}"/></td>
                <td>
                    <c:set var = "request" scope = "session" value = "Бронировать"/>
                    <c:if test = "${sessionScope.lang.toString() eq 'en'}">
                        <c:set var = "request" scope = "session" value = "Booking"/>
                    </c:if>
                    <form action="Controller" method="get">
                        <input type="hidden" name="command" value="ADD_REQUEST"/>
                        <input type="hidden" name="type" value="${requestScope.type}"/>
                        <input type="hidden" name="cost" value="${hostel.cost}"/>
                        <input type="hidden" name="rooms" value="${requestScope.rooms}"/>
                        <input type="hidden" name="days" value="${requestScope.days}"/>
                        <input type="hidden" name="date" value="${requestScope.date}"/>
                        <input type="hidden" name="hostel" value="${hostel.id}"/>
                        <input type="submit" value="${request}"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

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