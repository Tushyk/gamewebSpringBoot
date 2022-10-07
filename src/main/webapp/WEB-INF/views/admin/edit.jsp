<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <jsp:include page="../parts/sidebar.jsp"/>
        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="m-4 border-dashed view-height">
                <div class="mt-4 ml-4 mr-4">
                    <c:url var="edit_url" value="/admin/account/update"/>
                    <form:form method="post" modelAttribute="loggedUser" action="${edit_url}">
                        <form:hidden path="id"/>
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">change credentials</h3></div>
                            <div class="col d-flex justify-content-end mb-2">
                                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">save
                                </button>
                            </div>
                        </div>
                        <table class="table borderless">
                            <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>new login</h4></th>
                                <td class="col-7">

                                    <form:input path="username" placeholder="username"/>
                                    <form:errors path="username"/>
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>new email</h4></th>
                                <td class="col-7">
                                    <form:input path="email" placeholder="email"/>
                                    <form:errors path="email"/>
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>old password</h4></th>
                                <td class="col-7">
                                    <input type="password" name="oldPassword" placeholder="old password"/>
                                    <form:errors path="id"/>
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>new password</h4></th>
                                <td class="col-7">
                                    <form:input type="password" path="password" placeholder="password"/>
                                    <form:errors path="password"/>
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>repeat password</h4></th>
                                <td class="col-7">
                                    <form:input type="password" path="matchingPassword" placeholder="repeat password"/>
                                    <form:errors path=""/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
