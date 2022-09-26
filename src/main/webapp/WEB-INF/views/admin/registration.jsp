<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <form:form modelAttribute="user" method="post">
                    Enter your username<form:input path="username"  />
                    <form:errors path="username"/>
                    Enter your email<form:input path="email"/>
                    <form:errors path="email"/>
                    Create password<form:input type="password" path="password" />
                    <form:errors path="password"/>
                    Repeat password<form:input type="password" path="matchingPassword"/>
                    <form:errors path=""/>
                    <input type="submit"/>
                </form:form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
