<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <form method="POST" class="padding-small text-center">
                    <h1 class="text-color-darker">sign in</h1>
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="password">
                    </div>
                    <button class="btn btn-color rounded-0" type="submit">Zaloguj</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
