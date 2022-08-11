<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 11.08.2022
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url var="edit_url" value="/super-admin/update-game"/>
<form:form method="post" modelAttribute="game" cssClass="some-class" action="${edit_url}">
    <form:hidden path="id"/>
    <form:hidden path="avgRating"/>
    <form:hidden path="numberOfRatings"/>
    <form:input path="title"/>
    <form:errors path="title"/>
    <form:input type="date" path="releaseDate"/>
    <form:errors path="releaseDate"/>
    <form:select path="publisher">
        <form:options items="${publishers}" itemLabel="name" itemValue="id"/>
    </form:select>
    <form:select path="genre">
        <form:options items="${genres}" itemLabel="name" itemValue="id"/>
    </form:select>
    <form:checkboxes path="platforms" items="${platforms}" itemLabel="name" itemValue="id"/>
    <input type="submit"/>
</form:form>
</body>
</html>
