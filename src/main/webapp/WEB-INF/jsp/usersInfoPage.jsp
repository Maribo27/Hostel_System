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
    <title>User data</title>
</head>
<body>

<div style="padding:20px;"></div>
<div class="table-container">
    <table>
        <tr>
            <c:choose>
                <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                    <th>ID</th>
                    <th>Username</th>
                    <th>eMail</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Lastname</th>
                    <th>Discount</th>
                    <th>Status</th>
                    <th>Block reason</th>
                    <th>Block date</th>
                    <th>Unlock date</th>
                    <th>Action</th>
                </c:when>
                <c:otherwise>
                    <th>ID</th>
                    <th>Псевдоним</th>
                    <th>eMail</th>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Отчество</th>
                    <th>Скидка</th>
                    <th>Статус</th>
                    <th>Причина блокировки</th>
                    <th>Дата блокировки</th>
                    <th>Дата разблокировки</th>
                    <th>Действие</th>
                </c:otherwise>
            </c:choose>
        </tr>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.surname}"/></td>
                <td><c:out value="${user.lastname}"/></td>
                <td><c:out value="${user.discount}"/></td>
                <td><c:out value="${user.status}"/></td>
                <td><c:out value="${user.blockReason}"/></td>
                <td><c:out value="${user.blockDate}"/></td>
                <td><c:out value="${user.unlockDate}"/></td>
                <td>
                    <c:set var = "unlock" scope = "session" value = "Разблокировать"/>
                    <c:set var = "block" scope = "session" value = "Блокировать"/>
                    <c:if test = "${sessionScope.lang.toString() eq 'en'}">
                        <c:set var = "unlock" scope = "session" value = "Unlock User"/>
                        <c:set var = "block" scope = "session" value = "Block User"/>
                    </c:if>
                    <c:choose>
                        <c:when test = "${user.status.toString() eq 'banned'}">
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="UNLOCK"/>
                                <input type="hidden" name="id" value="${user.id}"/>
                                <input type="submit" value="${unlock}"/>
                            </form>
                        </c:when>
                        <c:when test = "${user.status.toString() eq 'user'}">
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="OPEN_BLOCK_PAGE"/>
                                <input type="hidden" name="id" value="${user.id}"/>
                                <input type="submit" value="${block}"/>
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
