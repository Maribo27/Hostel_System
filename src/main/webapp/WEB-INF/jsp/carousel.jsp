<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/carousel.css">
</head>
<body>
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
</body>
</html>