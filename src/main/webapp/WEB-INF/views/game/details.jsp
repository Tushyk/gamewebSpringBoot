<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 05.08.2022
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/game-list"/>">return</a>
<table border="1">
    <thead>
    <th>title</th>
    <th>released date</th>
    <th>genre</th>
    <th>publisher</th>
    </thead>
    <tbody>
    <tr>
        <td><c:out value="${game.title}"/></td>
        <td><c:out value="${game.releaseDate}"/></td>
        <td><c:out value="${game.genre.name}"/></td>
        <td><c:out value="${game.publisher.name}"/></td>
    </tr>
    </tbody>
</table>
<table border="1">
    <tr>
        <th>available platforms</th>
    </tr>
    <c:forEach items="${game.platforms}" var="platform">
        <tr>
            <td>${platform.name}</td>
        </tr>
    </c:forEach>
</table>
<sec:authorize access="isAuthenticated()">
    how would you rate a game:
    <form action="/game-rating/${game.id}">
        <input type="number" min="1" max="10" name="rate" id="rate">
        <input type="submit"/>
    </form>
</sec:authorize>
<h4>Discussions</h4>
<sec:authorize access="isAuthenticated()">
    <a href="<c:url value="/admin/add-discussion/${game.id}"/>">create discussion</a>
</sec:authorize>
<table>
<tbody>
<c:forEach items="${discussions}" var="discussion">
    <tr>
        <td><c:out value="discussion created by ${discussion.user.username}:"/></td>
        <td><a href="<c:url value="/discussion/details/${discussion.id}"/>"><c:out value="${discussion.topic}"/></a></td>
    </tr>
    <tr>
        <sec:authorize access="isAuthenticated()">
            <td>
                <a href="<c:url value="/article-form/confirm-delete/${game.id}"/>">delete</a>
            </td>
        </sec:authorize>
    </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
