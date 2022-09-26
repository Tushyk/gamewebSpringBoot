<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="m-4 border-dashed view-height">
                <div class="mt-4 ml-4 mr-4">
                    <form action="/rating-update/${rating.id}">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">We send activation link to your email address. Click to activate account</h3></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
