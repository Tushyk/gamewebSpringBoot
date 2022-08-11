<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 10.08.2022
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url var="edit_url" value="/admin/account/update"/>

<form action="${edit_url}">
  <label for="username">login</label><br>
  <input type="text" id="username" name="username" value="${login}"/><br>
  <input type="submit" value="Submit">
</form>
</body>
</html>
