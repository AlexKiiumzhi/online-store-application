<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<fmt:setBundle basename="outputs"/>
<c:set var="localeCode" value="${pageContext.response.locale}"/>
<fmt:message key="user.home_title" var="pageTitle"/>
<jsp:include page="userheader.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include><br><br>

</body>
</html>