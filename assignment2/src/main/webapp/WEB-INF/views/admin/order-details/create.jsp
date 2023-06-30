<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Order Detail</title>
    <!-- Thêm liên kết đến tệp CSS của Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Create Order Detail</h1>
    <form:form action="/admin/order-details/create" modelAttribute="orderDetail" method="post">
        <div class="form-group">
            <label for="price">Price:</label>
            <form:input type="number" class="form-control" id="price" path="price" required="true" />
        </div>
        <div class="form-group">
            <label for="quantity">Quantity:</label>
            <form:input type="number" class="form-control" id="quantity" path="quantity" required="true" />
        </div>
        <div class="form-group">
            <label for="product">Product:</label>
            <form:select class="form-control" path="product.id">
                <form:options items="${products}" itemValue="id" itemLabel="name" />
            </form:select>
        </div>
        <div class="form-group">
            <label for="order">Order:</label>
            <form:select class="form-control" path="order.id">
                <form:options items="${orders}" itemValue="id" itemLabel="id" />
            </form:select>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
    </form:form>
    <a href="/admin/order-details" class="btn btn-secondary">Back to List</a>
</div>

<!-- Thêm liên kết đến tệp JavaScript của Bootstrap -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
