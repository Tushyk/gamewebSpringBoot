<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>u sure want to delete this comment?</p>
<a href="<c:url value="/admin/comment/delete/${commentId}"/>">delete</a> <br/>
<a href="<c:url value="/discussion/details/${discussionId}"/>">cancel</a> <br/>
</body>
</html>
