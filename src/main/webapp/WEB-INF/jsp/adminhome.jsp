<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <fmt:message key="administrator.home_title" var="pageTitle"/>
        <jsp:include page="adminheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br><br>
        <div align="center">
            <h1><fmt:message key="delete.user"/> </h1>
        </div>
        <form class="w3-center" action="${pageContext.request.contextPath}/admin/user/delete" method="post">
            <label for="id1"><fmt:message key="User.delete"/></label><br>
            <input type="number" min="1" id="id1" name="id1" placeholder="id number"><br><br>
            <input type="submit" value=<fmt:message key="delete.user"/>>
        </form>

        <div align="center">
            <h1><fmt:message key="heading.admin.edit.user"/> </h1>
        </div>
        <form class="w3-center" action="${pageContext.request.contextPath}/admin/user/edit" method="post">
            <label for="id"><fmt:message key="user.id"/></label><br>
            <input type="number" min="1" id="id" name="id" placeholder="id number"><br><br>
            <label for="firstName"><fmt:message key="user.first_name.en"/></label><br>
            <input type="text" id="firstName" name="firstName"><br><br>
            <label for="lastName"><fmt:message key="user.last_name.ua"/></label><br>
            <input type="text" id="lastName" name="lastName"><br><br>
            <label for="email"><fmt:message key="user.email"/></label><br>
            <input type="text" id="email" name="email"><br><br>
            <label for="password"><fmt:message key="user.password"/></label><br>
            <input type="password" id="password" name="password" ><br><br>
            <label for="phoneNumber"><fmt:message key="user.phone"/></label><br>
            <input type="text" id="phoneNumber" name="phoneNumber" placeholder="00380XXXXXXXXX"><br><br>
            <input type="submit" value=<fmt:message key="User.Update"/>>
        </form>
    </body>
</html>