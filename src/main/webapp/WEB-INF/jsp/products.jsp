<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <body>
    <fmt:setBundle basename="outputs"/>
    <c:set var="localeCode" value="${pageContext.response.locale}"/>
    <fmt:message key="product.title" var="pageTitle"/>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include><br>
    <div class="w3-row-padding w3-margin-top">
        <fmt:message key="product.category"/>
    <form action="${pageContext.request.contextPath}/product/all" method="get">
        <select name="sortBy">

                    <option value="name" selected><fmt:message key="product.name"/></option>
                    <option value="brandName" selected><fmt:message key="product.brandName"/></option>
                    <option value="price" selected><fmt:message key="product.price"/></option>
                    <option value="category" selected><fmt:message key="product.category"/></option>

        </select>
        <br><input type="submit" value="<fmt:message key="submit.button"/>"/>
    </form>

    <table class="w3-table w3-bordered w3-striped">
        <thead>
        <tr>
            <th><fmt:message key="product.name"/></th>
            <th><fmt:message key="product.price"/></th>
            <th><fmt:message key="product.brandName"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
                        <tr>
                            <td><c:out value="${product.name}"/></td>
                            <td><c:out value="${product.brandName}"/></td>
                            <td><c:out value="${product.price}"/></td>
                        </tr>
        </c:forEach>
        <tbody>
    </table>

    </div>
    </body>
</html>