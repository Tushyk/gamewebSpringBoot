<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../parts/header.jsp"/>
<section class="dashboard-section">
  <div class="row dashboard-nowrap">
    <jsp:include page="../parts/sidebar.jsp"/>
    <div class="m-4 p-3 width-medium text-color-darker">
      <div class="m-4 border-dashed view-height">
        <div class="mt-4 ml-4 mr-4">
          <c:url var="edit_url" value="/admin/password/update"/>
          <form action="${edit_url}">
            <div class="row border-bottom border-3">
              <div class="col"><h3 class="color-header text-uppercase">change password</h3></div>
              <div class="col d-flex justify-content-end mb-2">
                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">save
                </button>
              </div>
            </div>
            <table class="table borderless">
              <tbody>
              <tr class="d-flex">
                <th scope="row" class="col-2"><h4>old password</h4></th>
                <td class="col-7">
                  <input type="password" class="w-100 p-1" name="oldPassword">
                </td>
              </tr>
              <tr class="d-flex">
                <th scope="row" class="col-2"><h4>new password</h4></th>
                <td class="col-7">
                  <input type="password" class="w-100 p-1" name="newPassword">
                </td>
              </tr>
              <tr class="d-flex">
                <th scope="row" class="col-2"><h4>repeat password</h4></th>
                <td class="col-7">
                  <input type="password" class="w-100 p-1" name="repeatPassword">
                </td>
              </tr>
              </tbody>
            </table>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>
<jsp:include page="../parts/footer.jsp"/>
