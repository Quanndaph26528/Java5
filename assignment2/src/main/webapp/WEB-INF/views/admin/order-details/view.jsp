<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Order Detail</title>
</head>
<body>
<h1>View Order Detail</h1>
<table>
    <tr>
        <th>ID:</th>
        <td>${orderDetail.id}</td>
    </tr>
    <tr>
        <th>Price:</th>
        <td>${orderDetail.price}</td>
    </tr>
    <tr>
        <th>Quantity:</th>
        <td>${orderDetail.quantity}</td>
    </tr>
    <tr>
        <th>Product:</th>
        <td>${orderDetail.product.name}</td>
    </tr>
    <tr>
        <th>Order:</th>
        <td>${orderDetail.order.id}</td>
    </tr>
</table>
<a href="/admin/order-details">Back to List</a>
</body>
</html>
