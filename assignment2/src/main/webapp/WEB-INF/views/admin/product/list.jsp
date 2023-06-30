<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>


<table border="1" style="width:100%" class="table">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Loại</th>
        <th>Img</th>
        <th>Action</th>
    </tr>
    <c:forEach var="items" items="${items}">
        <tr>
            <td>${items.id}</td>
            <td>${items.name}</td>
            <td>${items.price}</td>
            <td>${items.category.name}</td>
            <td>${items.image}</td>
            <td>
               <a href="/admin/product/edit/${items.id}" class="btn btn-sm btn-danger">Edit</a>
                <a href="/admin/product/delete/${items.id}" class="btn btn-sm btn-secondary">delete</a>

            </td>
        </tr>
    </c:forEach>
</table>