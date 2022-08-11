<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <!-- fix action, method -->
                <!-- add name attribute for all inputs -->
                <form:form modelAttribute="user" cssClass="some-class">
                    <form:hidden path="id"/>
                    Enter your username<form:input path="username"  />
                    <form:errors path="username"/>
                    Create password<form:input type="password" path="password" />
                    <form:errors path="password"/>
                    <input type="submit"/>
                </form:form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
