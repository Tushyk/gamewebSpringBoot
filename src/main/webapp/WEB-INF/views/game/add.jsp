<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="m-4 border-dashed view-height">
                <div class="mt-4 ml-4 mr-4">
                    <form:form method="post" modelAttribute="game">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">add game</h3></div>
                            <div class="col d-flex justify-content-end mb-2">
                                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">add
                                </button>
                            </div>
                        </div>
                        <form:hidden path="id"/>
                        <table class="table borderless">
                            <tbody>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>title</h4></th>
                                <td class="col-7">
                                    <form:input path="title"/>
                                    <form:errors path="title"/>
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>release date</h4></th>
                                <td class="col-7">
                                    <form:input type="date" path="releaseDate"/>
                                    <form:errors path="releaseDate"/>
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>publisher</h4></th>
                                <td class="col-7">
                                    <form:select path="publisher">
                                        <form:options items="${publishers}" itemLabel="name" itemValue="id"/>
                                    </form:select>
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>genre</h4></th>
                                <td class="col-7">
                                    <form:select path="genre">
                                        <form:options items="${genres}" itemLabel="name" itemValue="id"/>
                                    </form:select>
                                </td>
                            </tr>
                            <tr class="d-flex">
                                <th scope="row" class="col-2"><h4>platforms</h4></th>
                                <td class="col-7">
                                    <form:checkboxes path="platforms" items="${platforms}" itemLabel="name" itemValue="id"/>
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
