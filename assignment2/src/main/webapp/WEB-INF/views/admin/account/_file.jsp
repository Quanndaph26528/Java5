<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Account Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <form:form action="/account/create" modelAttribute="account" class="row g-3">
        <div class="col-md-6">
            <label for="username" class="form-label">Username:</label>
            <form:input path="username" id="username" class="form-control" placeholder="Enter username"/>
        </div>
        <div class="col-md-6">
            <label for="password" class="form-label">Password:</label>
            <form:input path="password" id="password" class="form-control" placeholder="Enter password"/>
        </div>
        <div class="col-md-6">
            <label for="fullname" class="form-label">Fullname:</label>
            <form:input path="fullname" id="fullname" class="form-control" placeholder="Enter fullname"/>
        </div>
        <div class="col-md-6">
            <label for="email" class="form-label">Email:</label>
            <form:input path="email" id="email" class="form-control" placeholder="Enter email"/>
        </div>
        <div class="col-md-6">
            <label for="photo" class="form-label">Photo:</label>
            <form:input path="photo" id="photo" class="form-control" placeholder="Enter photo URL"/>
        </div>
        <div class="col-md-6">
            <label class="form-check-label">Activated:</label>
            <form:checkbox path="activated" class="form-check-input"/>
        </div>
        <div class="col-md-6">
            <label class="form-check-label">Admin:</label>
            <form:checkbox path="admin" class="form-check-input"/>
        </div>
        <div class="col-12">
            <button type="submit" formaction="/admin/account/create" class="btn btn-primary">Create</button>
            <button type="submit" formaction="/admin/account/update" class="btn btn-primary">Update</button>
            <a href="/admin/account/index" class="btn btn-secondary">Reset</a>
        </div>
    </form:form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
