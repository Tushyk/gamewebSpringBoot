<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
  <div class="row dashboard-nowrap">
    <div class="m-4 p-3 width-medium text-color-darker">
      <div class="m-4 border-dashed view-height">
        <div class="mt-4 ml-4 mr-4">
          <c:url var="edit_url" value="/admin/comment/update"/>

          <form:form method="post" modelAttribute="comment" action="${edit_url}">
            <div class="row border-bottom border-3">
              <div class="col"><h3 class="color-header text-uppercase">edit comment</h3></div>
              <div class="col d-flex justify-content-end mb-2">
                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">save
                </button>
              </div>
            </div>
            <form:hidden path="discussion"/>
            <form:hidden path="user"/>
            <form:hidden path="createdOn"/>
            <form:hidden path="id"/>
            <table class="table borderless">
              <tbody>
              <tr class="d-flex">
                <th scope="row" class="col-2"><h4>text</h4></th>
                <td class="col-7">
                  <form:textarea  path="text"/>
                  <form:errors path="text"/>
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