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
                        <div class="col"><h3 class="color-header text-uppercase">${user.username}</h3></div>
                        <div class="col d-flex justify-content-end mb-2"><a href="javascript:history.back()" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">return</a></div>
                    </div>
                    <div class="row d-flex">
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">rated games</h3></div>
                        <div class="col-2"></div>
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">created discussions</h3></div>
                    </div>
                    <div class="row d-flex">
                        <div class="col-5 p-4">
                            <ul class="col-5 p-4 list-unstyled">
                                <c:forEach items="${ratings}" var="rating">
                                    <li><a href="<c:url value="/game-details/${rating.game.id}"/>">${rating.game.title}</a> rated: ${rating.rating}</li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="col-5 p-4">
                            <ul class="col-5 p-4 list-unstyled">
                                <c:forEach items="${discussions}" var="discussion">
                                    <li><a href="<c:url value="/discussion/details/${discussion.id}"/>">${discussion.topic}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
