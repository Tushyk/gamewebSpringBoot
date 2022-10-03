<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <form:form method="post" modelAttribute="user">
                <div class="form-group">
                    <form:hidden path="id"/>
                    <form:hidden path="username"/>
                    <form:hidden path="email"/>
                    <div class="form-group">
                        <form:input type="password" path="password" placeholder="password"/>
                        <form:errors path="password"/>
                    </div>
                    <div class="form-group">
                        <form:input type="password" path="matchingPassword" placeholder="repeat password"/>
                        <form:errors path=""/>
                    </div>
                    <div class="form-group form-group--buttons">
                        <button class="btn" type="submit">reset password</button>
                    </div>
                    </form:form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
