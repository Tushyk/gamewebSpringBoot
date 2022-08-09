<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../parts/header.jsp"/>
<section>
    <div class="row padding-small" style="margin: 0">
        <i class="fas fa-users icon-users"></i>
        <h1>${discussion.game.title}</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding"><h3 class="color-header text-uppercase">Topic: ${discussion.topic}----user: ${discussion.user.username}</h3></div>
                </div>
                <table class="table border-bottom schedules-content">
                    <tbody class="text-color-lighter">
                    <c:forEach items="${comments}" var="comment">
                        <tr>
                            <td><c:out value="${comment.user.username}-----created: ${comment.createdOn.toLocalDate()}-----time:${comment.createdOn.toLocalTime()}"/></td>
                        </tr>
                        <tr>
                            <td><c:out value="${comment.text}"/></td>

                            <c:if test="${comment.user.id == currentUser.id}">
                                <td>
                                    <a href="<c:url value="/admin/comment/confirm-delete/${comment.id}"/>">delete</a>
                                </td>
                                <td>
                                    <a href="<c:url value="/admin/comment/edit/${comment.id}"/>">edit</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <sec:authorize access="isAuthenticated()">
                    add comment
                    <form:form method="post" action="/discussion/details/${discussion.id}" modelAttribute="comment" cssClass="some-class">
                        <form:hidden path="id"/>
                        <form:textarea path="text" />
                        <form:errors path="text"/>
                        <input type="submit"/>
                    </form:form>
                </sec:authorize>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
