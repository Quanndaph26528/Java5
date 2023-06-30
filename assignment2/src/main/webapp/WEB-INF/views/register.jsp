<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký tài khoản</title>
    <!-- Các thẻ meta, CSS, và JavaScript khác -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Đăng ký tài khoản</h2>

    <%-- Hiển thị thông báo lỗi (nếu có) --%>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <%-- Hiển thị thông báo thành công (nếu có) --%>
    <c:if test="${not empty success}">
        <div class="alert alert-success" id="success-message">${success}</div>
    </c:if>

    <%-- Form đăng ký tài khoản --%>
    <form action="/register" method="POST" id="register-form">
        <div class="form-group">
            <label for="username">Tên tài khoản:</label>
            <input type="text" id="username" name="username" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="fullname">Họ và tên:</label>
            <input type="text" id="fullname" name="fullname" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Đăng ký</button>
    </form>

    <p>Đã có tài khoản? <a href="/login">Đăng nhập ngay</a></p>
</div>

<script>
    $(document).ready(function() {
        var successMessage = $("#success-message").text();
        if (successMessage !== "") {
            $("#success-modal").modal("show");
        }
    });
</script>

<!-- Modal hiển thị thông báo thành công -->
<div class="modal fade" id="success-modal" tabindex="-1" role="dialog" aria-labelledby="success-modal-label" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="success-modal-label">Thông báo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Đóng">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>${success}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
