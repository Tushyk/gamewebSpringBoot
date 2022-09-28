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
      <h4>specify searching:</h4>
      <form class="padding-small text-left" action="/game-list-specify">
        genre: <select id="genre" name="genre">
        <option value="default">default</option>
        <c:forEach items="${genres}" var="genre">
          <option value="${genre.name}">${genre.name}</option>
        </c:forEach>
        </select>
        year: <select id="year" name="year">
          <option value="default">default</option>
          <option value="2000">2000</option>
          <option value="2001">2001</option>
          <option value="2002">2002</option>
          <option value="2003">2003</option>
          <option value="2004">2004</option>
          <option value="2005">2005</option>
          <option value="2006">2006</option>
          <option value="2007">2007</option>
          <option value="2008">2008</option>
          <option value="2009">2009</option>
          <option value="2010">2010</option>
          <option value="2011">2011</option>
          <option value="2012">2012</option>
          <option value="2013">2013</option>
          <option value="2014">2014</option>
          <option value="2015">2015</option>
          <option value="2016">2016</option>
          <option value="2017">2017</option>
          <option value="2018">2018</option>
          <option value="2019">2019</option>
          <option value="2020">2020</option>
          <option value="2021">2021</option>
          <option value="2022">2022</option>
        </select>
        platform: <select id="platform" name="platform">
          <option value="default">default</option>
        <c:forEach items="${platforms}" var="platform">
          <option value="${platform.name}">${platform.name}</option>
        </c:forEach>
        </select>
        <input type="submit">
      </form>
      <div class="dashboard-content border-dashed p-3 m-3 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
          <div class="col noPadding"><h3 class="color-header text-uppercase">game list</h3></div>
          <div class="col noPadding"><h3 class="color-header text-uppercase">${genre.name}</h3></div>
          <div class="col noPadding"><h3 class="color-header text-uppercase">${year}</h3></div>
          <div class="col noPadding"><h3 class="color-header text-uppercase">${platform.name}</h3></div>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
          <a href="/super-admin/add-game" class="btn btn-success rounded-0 text-light m-1">ADD GAME</a>
        </sec:authorize>
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
              <sec:authorize access="hasRole('ADMIN')">
                <td><a href="/super-admin/edit-game/${game.id}" class="btn btn-success rounded-0 text-light m-1">EDIT GAME</a></td>
                <td><a href="/super-admin/delete-game-confirm/${game.id}" class="btn btn-danger rounded-0 text-light m-1">DELETE GAME</a></td>
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