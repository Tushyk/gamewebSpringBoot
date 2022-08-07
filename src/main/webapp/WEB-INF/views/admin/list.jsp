<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 05.08.2022
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
  <tr>
    <th>username</th>
    <sec:authorize access="isAuthenticated()">
      <th>action</th>
    </sec:authorize>
  </tr>
  <c:forEach items="${users}" var="user">
  <tr>
    <td>${user.username}</td>
    <sec:authorize access="isAuthenticated()">
      <td>
        <a href="<c:url value="/article-form/confirm-delete/${user.id}"/>">delete</a>
        <a href="<c:url value="/article-form/update/${user.id}"/>">update</a>
      </td>
    </sec:authorize>
  </tr>
  </c:forEach>
</table>
</body>
</html>
