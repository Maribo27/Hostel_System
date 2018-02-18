<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/pagination.css">
</head>
<body>
<div class="input-data-form-help" style="padding-bottom: 20px;">
    <c:set var = "first" scope = "request" value = "${sessionScope.page.first}"/>
    <c:set var = "next" scope = "request" value = "${sessionScope.page.next}"/>
    <c:set var = "current" scope = "request" value = "${sessionScope.page.current}"/>
    <c:set var = "prev" scope = "request" value = "${sessionScope.page.prev}"/>
    <c:set var = "last" scope = "request" value = "${sessionScope.page.last}"/>
    <c:set var = "firstPage" scope = "request" value = "${sessionScope.page.firstPage}"/>
    <c:set var = "prevPage" scope = "request" value = "${sessionScope.page.prevPage}"/>
    <c:set var = "nextPage" scope = "request" value = "${sessionScope.page.nextPage}"/>
    <c:set var = "lastPage" scope = "request" value = "${sessionScope.page.lastPage}"/>
    <c:if test="${not empty requestScope.page}">
        <c:set var = "first" scope = "request" value = "${requestScope.page.first}"/>
        <c:set var = "next" scope = "request" value = "${requestScope.page.next}"/>
        <c:set var = "current" scope = "request" value = "${requestScope.page.current}"/>
        <c:set var = "prev" scope = "request" value = "${requestScope.page.prev}"/>
        <c:set var = "last" scope = "request" value = "${requestScope.page.last}"/>
        <c:set var = "firstPage" scope = "request" value = "${requestScope.page.firstPage}"/>
        <c:set var = "prevPage" scope = "request" value = "${requestScope.page.prevPage}"/>
        <c:set var = "nextPage" scope = "request" value = "${requestScope.page.nextPage}"/>
        <c:set var = "lastPage" scope = "request" value = "${requestScope.page.lastPage}"/>
    </c:if>

    <ul class="pagination2 border notranslate">
        <c:choose>
            <c:when test = "${first == last}">
                <li> <a class="not-active">&laquo;</a> </li>
                <li> <a class="active">${current}</a> </li>
                <li> <a class="not-active">&raquo;</a> </li>
            </c:when>
            <c:when test = "${current == first}">
                <li> <a class="not-active">&laquo;</a> </li>
                <li> <a class="active">${current}</a> </li>
                <li> <a href="${nextPage}">${next}</a> </li>
                <li> <a href="${lastPage}">&raquo;</a> </li>
            </c:when>
            <c:when test = "${current == last}">
                <li> <a href="${firstPage}">&laquo;</a> </li>
                <li> <a href="${prevPage}">${prev}</a> </li>
                <li> <a class="active">${current}</a> </li>
                <li> <a class="not-active">&raquo;</a> </li>
            </c:when>
            <c:otherwise>
                <li> <a href="${firstPage}">&laquo;</a> </li>
                <li> <a href="${prevPage}">${prev}</a> </li>
                <li> <a class="active">${current}</a> </li>
                <li> <a href="${nextPage}">${next}</a> </li>
                <li> <a href="${lastPage}">&raquo;</a> </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
</body>
</html>