<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        .header {
            width: 100%;
            margin: 0 0 60px;
            background-color: #333333;
            padding: 5px 0;
            position: relative;

        }

        .header-menu {
            list-style-type: none;
            text-align: center;
            margin: 0 auto;
            padding: 0;
        }

        .header-nav__item {
            display: inline-block;
        }

        .header-nav__item a:hover, .header-nav__item a:active {
            text-decoration: none;
            outline: none;
        }

        .header-nav__item a {
            display: block;
            width: auto;
            height: 30px;
            padding: 0 30px;
            text-decoration: none;
            color: white;
            border: 2px solid lightgray;
        }

        .sitename {
            margin-left: 10px;
            color: khaki;
        }

        .secondname {
            margin-left: 10px;
            color: khaki;
        }

        .text {
            margin-top: 25px;
            margin-right: 20px;
            float: right;
            color: khaki;
        }

        .logout, .login, .registration {
            display: block;
            float: right;
            color: black;
            margin-top: 25px;
            margin-right: 20px;
            width: 115px;
            height: 35px;
            line-height: 35px;
            background-color: whitesmoke;
            border: 1px solid gray;
            text-align: center;
            text-decoration: none;
        }

        .logout:hover, .logout:active, .login:hover, .login:active, .registration:hover, .registration:active {
            text-decoration: none;
            outline: none;
        }

    </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<header>
    <div class="header">
        <C:if test="${not empty Account}">
            <div class="text">Hi, ${Account.name}!</div>
            <a class="logout"
               href="${pageContext.request.contextPath}/frontController?command=logout">${navName}<fmt:message
                    bundle="${i18n}" key="logout"/></a>
        </C:if>
        <c:if test="${empty Account and not (pageName eq 'Login')}">
            <a class="registration"
               href="${pageContext.request.contextPath}/frontController?command=RegistrationPage">${navName}<fmt:message
                    bundle="${i18n}" key="header.registr"/></a>
            <a class="login" href="${pageContext.request.contextPath}/frontController?command=login">${navName}
                <fmt:message key="login.login" bundle="${i18n}"/></a>
        </c:if>
        <c:if test="${empty Account and  (pageName eq 'Login')}">
            <a class="registration"
               href="${pageContext.request.contextPath}/frontController?command=RegistrationPage">${navName}<fmt:message
                    bundle="${i18n}" key="header.registr"/></a>
        </c:if>
        <h1 class="sitename">Roomking.com</h1>
        <h6 class="secondname">Choose your destiny. . .</h6>
        <ul class="header-menu">
            <li class="header-nav__item"><a
                    href="${pageContext.request.contextPath}/frontController?command=home">${navName}<fmt:message
                    key="header.home" bundle="${i18n}"/></a></li>
            <li class="header-nav__item"><a
                    href="${pageContext.request.contextPath}/frontController?command=rooms">${navName}<fmt:message
                    key="header.rooms" bundle="${i18n}"/></a></li>
            <li class="header-nav__item"><a
                    href="${pageContext.request.contextPath}/frontController?command=orders">${navName}<fmt:message
                    key="header.orders" bundle="${i18n}"/></a></li>
            <c:url var="path" value="/frontController?command=${sessionScope.pageName}"></c:url>
            <li class="header-nav__item"><a href="${path}&amp;locale=ru"><fmt:message key="header.locale.ru"
                                                                                      bundle="${i18n}"/></a></li>
            <li class="header-nav__item"><a href="${path}&amp;locale=en"><fmt:message key="header.locale.en"
                                                                                      bundle="${i18n}"/></a></li>
        </ul>
        <form class="header-form">
        </form>
    </div>
</header>
</body>
</html>
