<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<link rel="stylesheet" href="assets/css/carousel.css">
<link rel="stylesheet" href="assets/css/input_form.css">
<link rel="stylesheet" href="assets/css/navigation_bar.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="shortcut icon" href="assets/images/favicon.png">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <c:choose>
        <c:when test = "${sessionScope.lang.toString() eq 'en'}">
            <title>Hostel System</title>
        </c:when>
        <c:otherwise>
            <title>Поиск хостелов</title>
        </c:otherwise>
    </c:choose>
</head>

<body>
<c:choose>
    <c:when test = "${sessionScope.user != null}">
        <jsp:forward page="home.jsp" />
    </c:when>
</c:choose>
<div style="padding:20px;"></div>

<div id="sidebar">
    <section class="container">
        <div class="input-data-form">
            <c:choose>
                <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                    <h1>Enter your registration data</h1>
                </c:when>
                <c:otherwise>
                    <h1>Введите регистрационные данные</h1>
                </c:otherwise>
            </c:choose>
            <form action="Controller" method="get">
                <input type="hidden" name="command" value="REGISTER"/>
                <c:choose>
                    <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                        <input type="text" name="username" placeholder="Enter username (5 or more letters)" pattern="^[\w]{5,20}$" value="${requestScope.username}"/>
                        <input type="text" name="name" placeholder="Enter name (from 2 to 50 letters)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${requestScope.name}"/>
                        <input type="text" name="lastname" placeholder="Enter lastname (from 2 to 50 letters)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${requestScope.lastname}"/>
                        <input type="text" name="surname" placeholder="Enter surname (from 2 to 50 letters)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${requestScope.surname}"/>
                        <input type="email" name="email" placeholder="Enter email" pattern="^[\w]+@[A-Za-z]+\.[A-Za-z]{2,3}$" value="${requestScope.email}"/>
                        <input type="password" name="password" placeholder="Enter password (from 6 to 12 symbols)" pattern="^[\w]{6,12}$"/>
                        <input type="submit" value="Register"/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="username" placeholder="Введите псевдоним (5 или более символов)" pattern="^[\w]{5,20}$" value="${requestScope.username}"/>
                        <input type="text" name="name" placeholder="Введите имя (от 2 до 50 символов)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${requestScope.name}"/>
                        <input type="text" name="lastname" placeholder="Введите отчество (от 2 до 50 символов)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${requestScope.lastname}"/>
                        <input type="text" name="surname" placeholder="Введите фамилию (от 2 до 50 символов)" pattern="^[A-Za-zА-Яа-я]{2,50}$" value="${requestScope.surname}"/>
                        <input type="email" name="email" placeholder="Введите email" pattern="^[\w]+@[A-Za-z]+\.[A-Za-z]{2,3}$" value="${requestScope.email}"/>
                        <input type="password" name="password" placeholder="Введите пароль (от 6 до 12 символов)" pattern="^[\w]{6,12}$"/>
                        <input type="submit" value="Регистрация"/>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
        <div class="input-data-form-help">
            <c:choose>
                <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                    <p>Have an account? <a href="register.jsp">Click here to log in</a>.</p>
                </c:when>
                <c:otherwise>
                    <p>Уже есть аккаунт? <a href="register.jsp">Нажмите, чтобы войти</a>.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </section>
</div>

<div class="slideshow-carousel-wrapper">
    <input type="radio" checked="checked" id="slide1" class="slideshow-carousel-selector" name="slideshow-carousel-selector" />
    <input type="radio" id="slide2" class="slideshow-carousel-selector" name="slideshow-carousel-selector" />
    <input type="radio" id="slide3" class="slideshow-carousel-selector" name="slideshow-carousel-selector" />

    <ul class="slideshow-carousel-items">
        <li class="slideshow-carousel-item slideshow-carousel-item-one">
        </li>
        <li class="slideshow-carousel-item slideshow-carousel-item-two">
        </li>
        <li class="slideshow-carousel-item slideshow-carousel-item-three">
        </li>
    </ul>

    <ul class="slideshow-carousel-labels">
        <li class="slideshow-carousel-label">
            <label for="slide1"></label>
        </li>
        <li class="slideshow-carousel-label">
            <label for="slide2"></label>
        </li>
        <li class="slideshow-carousel-label">
            <label for="slide3"></label>
        </li>
    </ul>
</div>
<div style="padding:15px;"></div>

<ul id="navigation_bar">
    <c:choose>
        <c:when test = "${sessionScope.lang.toString() eq 'en'}">
            <li><a href="register.jsp">Log In</a></li>
            <li><a href="#">Language</a>
                <ul>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=ru&page=index.jsp">Russian</a></li>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=en&page=index.jsp">English</a></li>
                </ul>
            </li>
            <li class="active"><a href="register.jsp">Home</a></li>
            <li class="system-name"><a>Hostel System</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="register.jsp">Войти</a></li>
            <li><a href="#">Язык</a>
                <ul>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=ru&page=index.jsp">Русский</a></li>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=en&page=index.jsp">Английский</a></li>
                </ul>
            </li>
            <li class="active"><a href="register.jsp">Домой</a></li>
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
