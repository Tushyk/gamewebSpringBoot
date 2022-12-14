<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../parts/header.jsp"/>
<section>
  <div class="row padding-small" style="margin: 0">
    <i class="fas fa-users icon-users"></i>
    <h1>user list:</h1>
    <hr>
    <div class="orange-line w-100"></div>
  </div>
</section>
<section class="dashboard-section">
  <div class="row dashboard-nowrap">
    <div class="m-4 p-3 width-medium">
      <form action="/userList" class="padding-small text-center">
        <div class="form-group">
          <input type="text" class="form-control" id="name" name="name" placeholder="type user to find">
        </div>
        <button class="btn btn-color rounded-0" type="submit">search</button>
      </form>
      <div class="dashboard-content border-dashed p-3 m-3 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
          <div class="col noPadding"><h3 class="color-header text-uppercase">user list</h3></div>
        </div>
        <table class="table border-bottom schedules-content">
          <thead>
          <tr class="text-color-darker">
            <th scope="col" class="col-1">username</th>
            <th scope="col" class="col-2">number of rated games</th>
            <th scope="col" class="col-2">number of created discussions</th>
          </tr>
          </thead>
          <tbody class="text-color-lighter">
          <c:forEach items="${users}" var="user">
            <tr>
              <td><a class="nav-link color-header" href="user-account/${user.id}">${user.username}</a></td>
              <td>${user.numberOfRatedGames}</td>
              <td>${user.numberOfDiscussions}</td>
              <sec:authorize access="hasRole('ADMIN')">
                <c:if test="${user.enabled == 1}">
                  <td class="col-2 center">
                    <a href="/super-admin/block-user/${user.id}" class="btn btn-danger rounded-0 text-light m-1">block</a>
                  </td>
                </c:if>
                <c:if test="${user.enabled == 0}">
                  <td class="col-2 center">
                    <a href="/super-admin/unblock-user/${user.id}" class="btn btn-success rounded-0 text-light m-1">unblock</a>
                  </td>
                </c:if>
              </sec:authorize>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
