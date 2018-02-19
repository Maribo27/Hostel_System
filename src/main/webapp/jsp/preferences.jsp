<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/input_form parameters.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.png">
    <script src="${pageContext.request.contextPath}/assets/js/support/jquery-3.2.1.min.js"></script>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="locale.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="locale.title.preferences" var="preferences"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.username" var="username"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.name" var="name"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.last.name" var="lastName"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.email" var="email"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.current.password" var="password"/>
    <fmt:message bundle="${loc}" key="locale.message.enter.new.password" var="newPassword"/>
    <fmt:message bundle="${loc}" key="locale.button.change" var="change"/>
    <fmt:message bundle="${loc}" key="locale.button.change.password" var="changePassword"/>
    <fmt:message bundle="${loc}" key="locale.title.change.data" var="changeData"/>
    <fmt:message bundle="${loc}" key="locale.title.delete.account" var="deleteAccount"/>
    <fmt:message bundle="${loc}" key="locale.button.delete" var="delete"/>

    <fmt:message bundle="${loc}" key="locale.input.message.user.content" var="userContent"/>
    <fmt:message bundle="${loc}" key="locale.input.message.username.length" var="usernameLength"/>
    <fmt:message bundle="${loc}" key="locale.input.message.password.length" var="passwordLength"/>
    <fmt:message bundle="${loc}" key="locale.input.message.name.first" var="nameFirst"/>
    <fmt:message bundle="${loc}" key="locale.input.message.name.length" var="nameLength"/>
    <fmt:message bundle="${loc}" key="locale.input.message.name.content" var="nameContent"/>
    <fmt:message bundle="${loc}" key="locale.input.message.email.content" var="emailContent"/>
    <fmt:message bundle="${loc}" key="locale.input.message.email.symbol" var="emailSymbol"/>

    <title> ${preferences} | ${sessionScope.user.personalInfo.username} | Hostel System</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header/header.jsp"/>
<div style="width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    display: flex;
    align-items: center;
    align-content: center;
    justify-content: center;
    overflow: auto;  ">
    <div class="parameters-form">
        <table class="preferences-table">
            <tr>
                <th>${changeData}</th>
                <th>${changePassword}</th>
            </tr>
            <tr>
                <td rowspan="3" width="50%">
                    <form action="${pageContext.request.contextPath}/hostel_system" method="post">
                        <input type="hidden" name="command" value="CHANGE_USER_DATA"/>
                        <label for="username">
                            <input type="text" id="username" name="username" placeholder="${username}" pattern="^[\w][\w\.\_\d]{4,20}$" value="${sessionScope.user.personalInfo.username}" maxlength="20" minlength="5" required>
                            <ul class="input-requirements">
                                <li>${usernameLength}</li>
                                <li>${userContent}</li>
                            </ul>
                        </label>

                        <label for="name">
                            <input type="text" id="name" name="name" placeholder="${name}" pattern="^[A-ZА-Я][a-zа-я]{1,50}$" value="${sessionScope.user.personalInfo.name}" maxlength="50" minlength="2" required/>
                            <ul class="input-requirements">
                                <li>${nameLength}</li>
                                <li>${nameFirst}</li>
                                <li>${nameContent}</li>
                            </ul>
                        </label>

                        <label for="last-name">
                            <input type="text" id="last-name" name="last-name" placeholder="${lastName}" maxlength="50" value="${sessionScope.user.personalInfo.lastName}"/>
                            <ul class="input-requirements">
                                <li>${nameLength}</li>
                                <li>${nameFirst}</li>
                                <li>${userContent}</li>
                            </ul>
                        </label>

                        <label for="surname">
                            <input type="text" id="surname" name="surname" placeholder="${surname}" pattern="^[A-ZА-Я][a-zа-я]{1,50}$" value="${sessionScope.user.personalInfo.surname}" maxlength="50" minlength="2" required/>
                            <ul class="input-requirements">
                                <li>${nameLength}</li>
                                <li>${nameFirst}</li>
                                <li>${userContent}</li>
                            </ul>
                        </label>

                        <label for="email">
                            <input type="email" id="email" name="email" placeholder="${email}" pattern="^[\w\.\_\d\-]+@[A-Za-z]+.[A-Za-z]{2,3}$" value="${sessionScope.user.personalInfo.email}" required/>
                            <ul class="input-requirements">
                                <li>${emailContent}</li>
                                <li>${emailSymbol}</li>
                            </ul>
                        </label>

                        <label for="password">
                            <input type="password" id="password" name="password" placeholder="${password}" pattern="^[\w\d\.\_]{5,12}$" maxlength="12" minlength="5" required/>
                            <ul class="input-requirements">
                                <li>${passwordLength}</li>
                                <li>${userContent}</li>
                            </ul>
                        </label>
                        <input type="submit" value="${change}"/>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/hostel_system" method="post">
                        <input type="hidden" name="command" value="CHANGE_PASSWORD"/>
                        <label for="new-password">
                            <input type="password" id="new-password" name="new-password" placeholder="${newPassword}" pattern="^[\w\d\.\_]{5,12}$" maxlength="12" minlength="5" required/>
                            <ul class="input-requirements">
                                <li>${passwordLength}</li>
                                <li>${userContent}</li>
                            </ul>
                        </label>
                        <label for="change-confirm-password">
                            <input type="password" id="change-confirm-password" name="change-confirm-password" placeholder="${password}" pattern="^[\w\d\.\_]{5,12}$" maxlength="12" minlength="5" required/>
                            <ul class="input-requirements">
                                <li>${passwordLength}</li>
                                <li>${userContent}</li>
                            </ul>
                        </label>
                        <input type="submit" value="${changePassword}"/>
                    </form>
                </td>
            </tr>
            <tr>
                <th>${deleteAccount}</th>
            </tr>
            <tr>
                <td>

                    <form action="${pageContext.request.contextPath}/hostel_system" method="post">
                        <label for="delete-confirm-password">
                            <input type="password" id="delete-confirm-password" name="delete-confirm-password" placeholder="${password}" pattern="^[\w\d\.\_]{5,12}$" maxlength="12" minlength="5" required/>
                            <ul class="input-requirements">
                                <li>${passwordLength}</li>
                                <li>${userContent}</li>
                            </ul>
                        </label>
                        <input type="hidden" name="command" value="DELETE_USER"/>
                        <input type="submit" value="${delete}"/>
                    </form>
                </td>
            </tr>
        </table>
        <h2>${requestScope.error}</h2>
    </div>
</div>
<script src="assets/js/validation/validation_${sessionScope.lang}.js"></script>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
<jsp:include page="/WEB-INF/jsp/topButton.jsp"/>
</html>