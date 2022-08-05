<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 04.08.2022
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form modelAttribute="user" cssClass="some-class">
    <form:hidden path="id"/>
    <form:input path="username"/>
    <form:errors path="username"/>
    <form:input path="password"/>
    <form:errors path="password"/>
    <input type="submit"/>
</form:form>
</body>
</html>
