<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Order</title>
    <!-- Thêm liên kết đến tệp CSS của Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script>
        function calculatePrice() {
            var quantity = document.getElementById('quantity').value;
            var price = document.getElementById('price').value;
            var totalPrice = parseFloat(quantity) * parseFloat(price);
            document.getElementById('totalPrice').value = totalPrice.toFixed(2);
        }
    </script>
</head>
<body>
<div class="container">
    <h3>Edit Order</h3>
    <form:form action="/admin/order-details/update" method="post" modelAttribute="orderDetail">
        <input type="hidden" name="id" value="${orderDetail.id}" />
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" class="form-control" name="price" id="price" value="${orderDetail.price}" oninput="calculatePrice()" />
        </div>
        <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input type="text" class="form-control" name="quantity" id="quantity" value="${orderDetail.quantity}" oninput="calculatePrice()" />
        </div>
        <div class="form-group">
            <label for="totalPrice">Total Price:</label>
            <input type="text" class="form-control" name="totalPrice" id="totalPrice" value="${orderDetail.price * orderDetail.quantity}" readonly />
        </div>
        <div class="form-group">
            <label for="product">Product:</label>
            <select class="form-control" name="product.id">
                <c:forEach items="${products}" var="product">
                    <option value="${product.id}" ${product.id eq orderDetail.product.id ? 'selected' : ''}>${product.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="order">Order:</label>
            <select class="form-control" name="order.id">
                <c:forEach items="${orders}" var="order">
                    <option value="${order.id}" ${order.id eq orderDetail.order.id ? 'selected' : ''}>${order.id}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form:form>
</div>

<!-- Thêm liên kết đến tệp JavaScript của Bootstrap -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
