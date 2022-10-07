<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="nav flex-column long-bg">
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/account/edit">
            <span>edit login</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin/confirm-delete-account">
            <span>delete account</span>
            <i class="fas fa-angle-right"></i>
        </a>
    </li>
</ul>
