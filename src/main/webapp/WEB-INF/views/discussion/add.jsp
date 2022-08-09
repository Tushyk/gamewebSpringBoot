<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <!-- fix action, method -->
                <!-- add name attribute for all inputs -->
                <form:form method="post" modelAttribute="discussion" action="/admin/add-discussion/${gameId}" cssClass="some-class">
                    <form:hidden path="id"/>
                    Enter discussion title:<form:input path="topic"  />
                    <form:errors path="topic"/>
                    <input type="submit"/>
                </form:form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
