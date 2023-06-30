<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary p-0">
  <div class="navbar-nav collapse navbar-collapse">
    <c:if test="${not empty sessionScope.admin}">
      <a class="nav-item nav-link" href="/admin/category/index">Quản lý nhóm sản phẩm</a>
      <a class="nav-item nav-link" href="/admin/product/index">Quản lý sản phẩm</a>
      <a class="nav-item nav-link" href="/admin/account/index">Quản lý tài khoản</a>
    </c:if>
  </div>

  <ul class="navbar-nav ml-auto">
    <li class="nav-item no-arrow">
      <a class="nav-link dropdown-toggle p-0" data-bs-toggle="dropdown" href="#">
        <c:if test="${sessionScope.user.isAdmin()}">
          <!-- Hiển thị nội dung cho vai trò admin -->
        </c:if>
        <img alt="" class="rounded-circle" style="width:60px" src="/static/images/user.svg" />
      </a>
      <div class="dropdown-menu dropdown-menu-end">
        <a class="dropdown-item" href="/logout">Đăng xuất</a>
      </div>
    </li>
  </ul>
</nav>
