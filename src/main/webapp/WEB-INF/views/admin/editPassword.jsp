<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 10.08.2022
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url var="edit_url" value="/admin/password/update"/>

<form action="${edit_url}">
  <label for="oldPassword">enter old password:</label><br>
  <input type="password" id="oldPassword" name="oldPassword"><br>
  <label for="newPassword">enter new password:</label><br>
  <input type="password" id="newPassword" name="newPassword"><br><br>
  <label for="repeatPassword">repeat new password:</label><br>
  <input type="password" id="repeatPassword" name="repeatPassword"><br><br>
  <input type="submit" value="Submit">
</form>
</body>
</html>
