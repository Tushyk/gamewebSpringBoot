<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../parts/header.jsp"/>
<section>
  <div class="row padding-small" style="margin: 0">
    <i class="fas fa-users icon-users"></i>
    <h1>game list:</h1>
    <hr>
    <div class="orange-line w-100"></div>
  </div>
</section>
<section class="dashboard-section">
  <div class="row dashboard-nowrap">
    <div class="m-4 p-3 width-medium">
      <form action="/gameList" class="padding-small text-center">
        <div class="form-group">
          <input type="text" class="form-control" id="title" name="title" placeholder="give game title">
        </div>
        <button class="btn btn-color rounded-0" type="submit">search</button>
      </form>
      <div class="dashboard-content border-dashed p-3 m-3 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
          <div class="col noPadding"><h3 class="color-header text-uppercase">game list</h3></div>
        </div>
        <table class="table border-bottom schedules-content">
          <thead>
          <tr class="text-color-darker">
            <th scope="col" class="col-1">title</th>
            <th scope="col" class="col-2">rating</th>
            <th scope="col" class="col-2">popularity</th>
          </tr>
          </thead>
          <tbody class="text-color-lighter">
          <c:forEach items="${games}" var="game">
            <tr>
              <td><a class="nav-link color-header" href="game-details/${game.id}">${game.title}</a></td>
              <td>${game.avgRating}</td>
              <td>${game.numberOfRatings}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</section>
<jsp:include page="../parts/footer.jsp"/>