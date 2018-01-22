<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<link rel="stylesheet" href="../../assets/css/carousel.css">
<link rel="stylesheet" href="../../assets/css/input_form.css">
<link rel="stylesheet" href="../../assets/css/navigation_bar.css">
<link rel="stylesheet" href="../../assets/css/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="shortcut icon" href="../../assets/images/favicon.png">
<head>
    <title>Hostels</title>
</head>
<body>


<div style="padding:20px;"></div>
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
        <c:forEach items="${requestScope.hostels}" var="hostel">
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
                    <c:set var = "delete" scope = "session" value = "Удалить"/>
                    <c:set var = "request" scope = "session" value = "Бронировать"/>
                    <c:if test = "${sessionScope.lang.toString() eq 'en'}">
                        <c:set var = "delete" scope = "session" value = "Delete"/>
                        <c:set var = "request" scope = "session" value = "Booking"/>
                    </c:if>
                    <c:choose>
                        <c:when test = "${sessionScope.user.status.toString() eq 'admin'}">
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="DELETE_HOSTEL"/>
                                <input type="hidden" name="hostel" value="${hostel.id}"/>
                                <input type="submit" value="${delete}"/>
                            </form>
                        </c:when>
                        <c:when test = "${sessionScope.user.status.toString() eq 'user'}">
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="ADD_REQUEST"/>
                                <input type="hidden" name="hostel" value="${hostel.id}"/>
                                <input type="submit" value="${request}"/>
                            </form>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
<div class="input-data-form-help">
    <c:set var = "first" scope = "request" value = "${requestScope.page.first}"/>
    <c:set var = "next" scope = "request" value = "${requestScope.page.next}"/>
    <c:set var = "current" scope = "request" value = "${requestScope.page.current}"/>
    <c:set var = "prev" scope = "request" value = "${requestScope.page.prev}"/>
    <c:set var = "last" scope = "request" value = "${requestScope.page.last}"/>
    <c:choose>
        <c:when test = "${first == last}">
        </c:when>
        <c:when test = "${current == first}">
            <p><c:out value="${current}"/>/<c:out value="${last}"/> <a href="${requestScope.page.nextPage}"><c:out value="${next}"/></a> ... <a href="${requestScope.page.lastPage}"><c:out value="${last}"/></a></p>
        </c:when>
        <c:when test = "${current == last}">
            <p><a href="${requestScope.page.firstPage}">1</a> ... <a href="${requestScope.page.prevPage}"><c:out value="${prev}"/></a> <c:out value="${current}"/>/<c:out value="${last}"/></p>
        </c:when>

        <c:otherwise>
            <p><a href="${requestScope.page.firstPage}">1</a> <a href="${requestScope.page.prevPage}"><c:out value="${prev}"/></a> <c:out value="${current}"/>/<c:out value="${last}"/> <a href="${requestScope.page.nextPage}"><c:out value="${next}"/></a> <a href="${requestScope.page.lastPage}"><c:out value="${last}"/></a></p>
        </c:otherwise>
    </c:choose>
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
