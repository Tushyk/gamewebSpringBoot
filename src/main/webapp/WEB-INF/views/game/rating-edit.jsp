<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 08.08.2022
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
you already have rated game: ${rating.rating}. do you want update rate?
<form action="/rating-update/${rating.id}">
    <input type="number" min="1" max="10" name="rate" id="rate">
    <input type="submit"/>
</form>
<a href="<c:url value="/game-details/${rating.game.id}"/>">back</a>
</body>
</html>
