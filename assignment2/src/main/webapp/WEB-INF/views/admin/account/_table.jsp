<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Accounts</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Photo</th>
            <th>Activated</th>
            <th>Admin</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td>${account.username}</td>
                <td>${account.password}</td>
                <td>${account.fullname}</td>
                <td>${account.email}</td>
                <td>${account.photo}</td>
                <td>${account.activated ? 'Yes' : 'No'}</td>
                <td>${account.admin ? 'Yes' : 'No'}</td>
                <td>
                    <a href="/admin/account/edit/${account.username}" class="btn btn-primary">Edit</a>
                    <a href="/admin/account/delete/${account.username}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
