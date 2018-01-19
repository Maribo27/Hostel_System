<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>
<html>
<head>
    <title>User Info</title>
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
            <c:choose>
                <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                    <h1>Change your data</h1>
                </c:when>
                <c:otherwise>
                    <h1>Измените свои данные</h1>
                </c:otherwise>
            </c:choose>
            <form action="Controller" method="get">
                <input type="hidden" name="command" value="CHANGE_USER_DATA"/>
                <c:choose>
                <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                    <input type="text" name="username" placeholder="Enter username (5 or more letters)" pattern="^[\w]{5,20}$" value="${sessionScope.username}"/>
                    <input type="text" name="name" placeholder="Enter name (from 2 to 50 letters)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.name}"/>
                    <input type="text" name="lastname" placeholder="Enter lastname (from 2 to 50 letters)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.lastname}"/>
                    <input type="text" name="surname" placeholder="Enter surname (from 2 to 50 letters)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.surname}"/>
                    <input type="email" name="email" placeholder="Enter email" pattern="^[\w]+@[A-Za-z]+\.[A-Za-z]{2,3}$" value="${sessionScope.email}"/>
                    <input type="password" name="password" placeholder="Enter password (from 6 to 12 symbols)" pattern="^[\w]{6,12}$"/>
                    <input type="submit" value="Change"/>
                </c:when>
                <c:otherwise>
                    <input type="text" name="username" placeholder="Введите псевдоним (5 или более символов)" pattern="^[\w]{5,20}$" value="${sessionScope.username}"/>
                    <input type="text" name="name" placeholder="Введите имя (от 2 до 50 символов)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.name}"/>
                    <input type="text" name="lastname" placeholder="Введите отчество (от 2 до 50 символов)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.lastname}"/>
                    <input type="text" name="surname" placeholder="Введите фамилию (от 2 до 50 символов)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${sessionScope.surname}"/>
                    <input type="email" name="email" placeholder="Введите email" pattern="^[\w]+@[A-Za-z]+\.[A-Za-z]{2,3}$" value="${sessionScope.email}"/>
                    <input type="password" name="password" placeholder="Введите пароль (от 6 до 12 символов)" pattern="^[\w]{6,12}$"/>
                    <input type="submit" value="Изменить"/>
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