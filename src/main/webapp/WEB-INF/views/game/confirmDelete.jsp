<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
  <div class="row dashboard-nowrap">
    <div class="m-4 p-3 width-medium">
      <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
          <form action="/app/recipe/delete" method="post">
            <div class="col noPadding"><h3 class="color-header text-uppercase">u sure want to delete this game?</h3></div>
            <a href="<c:url value="/super-admin/delete-game/${gameId}"/>" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">delete</a>
            <a href="<c:url value="/game-details/${gameId}"/>" class="btn btn-danger rounded-0 pt-0 pb-0 pr-4 pl-4">cancel </a>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
