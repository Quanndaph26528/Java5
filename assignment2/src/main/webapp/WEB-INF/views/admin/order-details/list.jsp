<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.7.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <hr>
    <h3>Order Details List</h3>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th>Product</th>
            <th>Order</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderDetails}" var="orderDetail">
            <tr>
                <td>${orderDetail.id}</td>
                <td>${orderDetail.price}</td>
                <td>${orderDetail.quantity}</td>
                <td>${orderDetail.price * orderDetail.quantity}</td>
                <td>${orderDetail.product.name}</td>
                <td>${orderDetail.order.id}</td>
                <td><a href="/admin/order-details/edit/${orderDetail.id}" class="btn btn-primary">Edit</a></td>
                <td><a href="/admin/order-details/delete/${orderDetail.id}" class="btn btn-danger">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>
    <a href="/admin/order-details/create" class="btn btn-success">Create New Order Detail</a>
</div>

<!-- Thêm liên kết đến tệp JavaScript của Bootstrap (tùy chọn) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.7.0/js/bootstrap.min.js"></script>
</body>
</html>
