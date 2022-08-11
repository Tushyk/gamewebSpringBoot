<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 11.08.2022
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" modelAttribute="game" cssClass="some-class">
    <form:hidden path="id"/>
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
