<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <fmt:message key="registration.title" var="pageTitle"/>
        <jsp:include page="header.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br><br>
        <form class="w3-center" action="${pageContext.request.contextPath}/register" method="post">
            <label for="firstName"><fmt:message key="registration.en_first_name"/></label><br>
            <input type="text" id="firstName" name="firstName"><br><br>
            <label for="lastName"><fmt:message key="registration.en_last_name"/></label><br>
            <input type="text" id="lastName" name="lastName"><br><br>
            <label for="email"><fmt:message key="registration.email"/></label><br>
            <input type="text" id="email" name="email"><br><br>
            <label for="password"><fmt:message key="registration.password"/></label><br>
            <input type="password" id="password" name="password"><br><br>
            <label for="phoneNumber"><fmt:message key="registration.phone"/></label><br>
            <input type="text" id="phoneNumber" name="phoneNumber" placeholder="00380XXXXXXXXX"><br><br>
            <input type="submit" value=<fmt:message key="registration.button"/>>
        </form>

    </body>
</html>