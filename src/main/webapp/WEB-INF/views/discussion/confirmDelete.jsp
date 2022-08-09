<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 09.08.2022
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
u sure want to delete this discussion?
<a href="<c:url value="/admin/discussion/delete/${discussionId}"/>">delete</a> <br/>
<a href="<c:url value="/game-details/${gameId}"/>">cancel</a> <br/>
</body>
</html>
