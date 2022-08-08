<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 07.08.2022
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${discussion.game.title}</h1>
<h2>Topic: ${discussion.topic} user: ${discussion.user.username}</h2>
<table border="1">
    <tbody>
    <c:forEach items="${comments}" var="comment">
        <tr>
            <td><c:out value="${comment.user.username}-----created: ${comment.createdOn.toLocalDate()}-----time:${comment.createdOn.toLocalTime()}"/></td>
        </tr>
        <tr>
            <td><c:out value="${comment.text}"/></td>
        </tr>
        <tr>
            <sec:authorize access="isAuthenticated()">
                <td>
                    <a href="<c:url value="/article-form/confirm-delete/${comment.id}"/>">delete</a>
                </td>
            </sec:authorize>
        </tr>
    </c:forEach>
    </tbody>
</table>
<sec:authorize access="isAuthenticated()">
add comment
<form:form method="post" action="/discussion/details/${discussion.id}" modelAttribute="comment" cssClass="some-class">
    <form:hidden path="id"/>
    <form:textarea path="text" />
    <form:errors path="text"/>
    <input type="submit"/>
</form:form>
</sec:authorize>
</body>
</html>
