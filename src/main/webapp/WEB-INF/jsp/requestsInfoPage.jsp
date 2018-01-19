<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<link rel="stylesheet" href="../../assets/css/carousel.css">
<link rel="stylesheet" href="../../assets/css/input_form.css">
<link rel="stylesheet" href="../../assets/css/navigation_bar.css">
<link rel="stylesheet" href="../../assets/css/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="shortcut icon" href="../../assets/images/favicon.png">
<link href="../../css/table.css" rel="stylesheet">
<link href="../../css/button.css" rel="stylesheet">
<head>
    <title>Request data</title>
</head>
<body>


<div style="padding:20px;"></div>
<div class="table-container">
    <table>
        <tr>
            <c:choose>
                <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Hostel ID</th>
                    <th>Type</th>
                    <th>Count of rooms</th>
                    <th>Count of days</th>
                    <th>Cost</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </c:when>
                <c:otherwise>
                    <th>ID</th>
                    <th>ID пользователя</th>
                    <th>ID хостела</th>
                    <th>Тип</th>
                    <th>Количество комнат</th>
                    <th>Количество дней</th>
                    <th>Стоимость</th>
                    <th>Дата</th>
                    <th>Статус</th>
                    <th>Действие</th>
                </c:otherwise>
            </c:choose>
        </tr>
        <c:forEach items="${requestScope.requests}" var="request" begin="${requestScope.begin}" end="${requestScope.end}">
            <tr>
                <td><c:out value="${request.id}"/></td>
                <td><c:out value="${request.userId}"/></td>
                <td><c:out value="${request.hostelId}"/></td>
                <td><c:out value="${request.type}"/></td>
                <td><c:out value="${request.room}"/></td>
                <td><c:out value="${request.days}"/></td>
                <td><c:out value="${request.cost}"/></td>
                <td><c:out value="${request.date}"/></td>
                <td><c:out value="${request.status}"/></td>
                <c:set var = "approve" scope = "session" value = "Одобрить"/>
                <c:set var = "denied" scope = "session" value = "Отказать"/>
                <c:set var = "cancel" scope = "session" value = "Отменить"/>
                <c:if test = "${sessionScope.lang.toString() eq 'en'}">
                    <c:set var = "approve" scope = "session" value = "Approve"/>
                    <c:set var = "denied" scope = "session" value = "Deny"/>
                    <c:set var = "cancel" scope = "session" value = "Cancel"/>
                </c:if>
                <c:choose>
                    <c:when test = "${sessionScope.user.status.toString() eq 'admin'}">
                        <td>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="CHANGE_REQUEST_STATUS"/>
                                <input type="hidden" name="request" value="${request.id}"/>
                                <input type="hidden" name="status" value="approve"/>
                                <input type="submit" value="${approve}"/>
                            </form>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="CHANGE_REQUEST_STATUS"/>
                                <input type="hidden" name="request" value="${request.id}"/>
                                <input type="hidden" name="status" value="denied"/>
                                <input type="submit" value="${denied}"/>
                            </form>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="CHANGE_REQUEST_STATUS"/>
                                <input type="hidden" name="request" value="${request.id}"/>
                                <input type="hidden" name="status" value="denied"/>
                                <input type="submit" value="${cancel}"/>
                            </form>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="input-data-form-help">
    <c:set var = "next" scope = "session" value = "${requestScope.next + 3}"/>
    <c:set var = "prev" scope = "session" value = "${requestScope.prev + 3}"/>
    <c:set var = "last" scope = "session" value = "${requestScope.last + 1}"/>
    <c:choose>
        <c:when test = "${requestScope.size <= requestScope.page}">
        </c:when>
        <c:when test = "${requestScope.next == 1}">
            <p><c:out value="${requestScope.next}"/>/<c:out value="${last}"/> <a href="${requestScope.gotonext}"><c:out value="${next + 1}"/></a> ... <a href="${requestScope.gotolast}"><c:out value="${last}"/></a></p>
        </c:when>
        <c:when test = "${requestScope.next == requestScope.last}">
            <p><a href="${requestScope.gotofirst}">1</a> ... <a href="${requestScope.gotoprev}"><c:out value="${prev + 1}"/></a> <c:out value="${requestScope.next}"/>/<c:out value="${last}"/></p>
        </c:when>

        <c:otherwise>
            <p><a href="${requestScope.gotofirst}">1</a> <a href="${requestScope.gotoprev}"><c:out value="${prev + 1}"/></a> <c:out value="${requestScope.next}"/>/<c:out value="${last}"/> <a href="${requestScope.gotonext}"><c:out value="${next + 1}"/></a> <a href="${requestScope.gotolast}"><c:out value="${last}"/></a></p>
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