<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 09.08.2022
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url var="edit_url" value="/admin/discussion/update"/>

<form:form method="post" modelAttribute="discussion" action="${edit_url}">
    <form:hidden path="id"/>
    <form:input path="topic"/>
    <form:errors path="topic"/><br/>
    <form:hidden path="game"/>
    <form:hidden path="user"/>
    <input type="submit" value="Save">
</form:form>
</body>
</html>
