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
                    <h1>Enter your data</h1>
                </c:when>
                <c:otherwise>
                    <h1>Введите свои данные</h1>
                </c:otherwise>
            </c:choose>
            <form action="Controller" method="get">
                <c:choose>
                    <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                        <input type="hidden" name="command" value="LOGIN"/>
                        <input type="text" name="username" value="${requestScope.username}" placeholder="Enter username or email" pattern="([\w\.]{3,10}@[A-Za-z]+\.[A-Za-z]{2,3}|[\w\.]{3,20})"/>
                        <input type="password" name="password" placeholder="Enter password" pattern="^[\w]{5,12}$"/>
                        <input type="submit" value="Log In"/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="command" value="LOGIN"/>
                        <input type="text" name="username" value="${sessionScope.username}" placeholder="Введите псевдоним или email" pattern="([\w\.]{3,10}@[A-Za-z]+\.[A-Za-z]{2,3}|[\w\.]{3,20})"/>
                        <input type="password" name="password" placeholder="Введите пароль" pattern="^[\w]{5,12}$"/>
                        <input type="submit" value="Войти"/>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
        <div class="input-data-form-help">
            <c:choose>
                <c:when test = "${sessionScope.lang.toString() eq 'en'}">
                    <p>Haven't account? <a href="index.jsp">Click here to register</a>.</p>
                </c:when>
                <c:otherwise>
                    <p>Ещё нет аккаунта? <a href="index.jsp">Нажмите, чтобы зарегистрироваться</a>.</p>
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
            <li><a href="index.jsp">Register</a></li>
            <li><a href="#">Language</a>
                <ul>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=ru&page=register.jsp">Russian</a></li>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=en&page=register.jsp">English</a></li>
                </ul>
            </li>
            <li class="active"><a href="index.jsp">Home</a></li>
            <li class="system-name"><a>Hostel System</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="index.jsp">Регистрация</a></li>
            <li><a href="#">Язык</a>
                <ul>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=ru&page=register.jsp">Русский</a></li>
                    <li><a href="Controller?command=CHANGE_LOCALE&lang=en&page=register.jsp">Английский</a></li>
                </ul>
            </li>
            <li class="active"><a href="index.jsp">Домой</a></li>
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
