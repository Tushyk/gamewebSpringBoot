<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 10.08.2022
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>u sure want to delete this account?</p>
<a href="<c:url value="/admin/account/delete"/>">delete</a> <br/>
<a href="<c:url value="/user-account/${userId}"/>">cancel</a> <br/>
</body>
</html>
