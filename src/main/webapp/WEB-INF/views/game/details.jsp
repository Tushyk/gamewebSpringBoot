<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="mt-4 ml-4 mr-4">
                    <div class="row border-bottom border-3">
                        <div class="col"><h3 class="color-header text-uppercase">Game details</h3></div>
                        <div class="col d-flex justify-content-end mb-2"><a href="javascript:history.back()" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">return</a></div>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td><a href="/super-admin/edit-game/${game.id}" class="btn btn-success rounded-0 text-light m-1">EDIT GAME</a></td>
                            <td><a href="/super-admin/delete-game-confirm/${game.id}" class="btn btn-danger rounded-0 text-light m-1">DELETE GAME</a></td>
                        </sec:authorize>
                    </div>

                    <table class="table borderless">
                        <tbody>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Title</th>
                            <td class="col-7">
                                <c:out value="${game.title}"/>
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Genre</th>
                            <td class="col-7"><c:out value="${game.genre.name}"/></td>
                        </tr>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Release date</th>
                            <td class="col-7">
                                <c:out value="${game.releaseDate}"/>
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Publisher</th>
                            <td class="col-7">
                                <c:out value="${game.publisher.name}"/>
                            </td>
                        </tr>
                        <sec:authorize access="isAuthenticated()">
                        <tr class="d-flex">
                            <th scope="row" class="col-2">your rate</th>
                            <td class="col-7">
                                <c:out value="${rating.rating}"/>
                            </td>
                        </tr>
                        </sec:authorize>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Average rate</th>
                            <td class="col-7">
                                <c:out value="${avgRate}"/>
                            </td>
                        </tr>
                        <sec:authorize access="isAuthenticated()">
                        <tr class="d-flex">
                            <th scope="row" class="col-2">rate game</th>
                            <td class="col-7">
                                    how would you rate a game:
                                    <form action="/game-rating/${game.id}">
                                        <input type="number" min="1" max="10" name="rate" id="rate">
                                        <input type="submit"/>
                                    </form>
                            </td>
                        </tr>
                        </sec:authorize>
                        </tbody>
                    </table>
                    <div class="row d-flex">
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Description</h3></div>
                        <div class="col-2"></div>
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">platforms</h3></div>
                    </div>
                    <div class="row d-flex">
                        <div class="col-5 p-4">
                            <p>tutaj dodac opis</p>
                        </div>
                        <div class="col-2"></div>
                        <ul class="col-5 p-4 list-unstyled">
                            <c:forEach items="${game.platforms}" var="platform">
                                <li>${platform.name}</li>
                            </c:forEach>
                        </ul>
                    </div>
                        <h4>Discussions</h4>
                        <sec:authorize access="isAuthenticated()">
                            <a href="<c:url value="/admin/add-discussion/${game.id}"/>">create discussion</a>
                        </sec:authorize>
                        <table border="1">
                            <tbody>
                            <c:forEach items="${discussions}" var="discussion">
                                <tr>
                                    <td><c:out value="discussion created by ${discussion.user.username}:"/></td>
                                    <td><a href="<c:url value="/discussion/details/${discussion.id}"/>"><c:out value="${discussion.topic}"/></a></td>
                                    <c:if test="${discussion.user.id == currentUser.id}">
                                        <td>
                                            <a href="<c:url value="/admin/discussion/confirm-delete/${discussion.id}"/>">delete</a>
                                        </td>
                                        <td>
                                            <a href="<c:url value="/admin/discussion/edit/${discussion.id}"/>">edit</a>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>