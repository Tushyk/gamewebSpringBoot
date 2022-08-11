<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 09.08.2022
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url var="edit_url" value="/admin/comment/update"/>

<form:form method="post" modelAttribute="comment" action="${edit_url}">
  <form:hidden path="id"/>
  <form:input path="text"/>
  <form:errors path="text"/><br/>
  <form:hidden path="discussion"/>
  <form:hidden path="user"/>
  <form:hidden path="createdOn"/>
  <input type="submit" value="Save">
</form:form>
</body>
</html>
