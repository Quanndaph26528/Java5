<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>

<table border="1" style="width:100%" class="table">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Action</td>
    </tr>
    <c:forEach var="items" items="${items}">
        <tr>
            <th>${items.id}</th>
            <th>${items.name}</th>
            <th>
                <a href="/admin/category/edit/${items.id}" class="btn btn-sm btn-danger">Edit</a>
                <a href="/admin/category/delete/${items.id}" class="btn btn-sm btn-danger">Delete</a>
            </th>

        </tr>
    </c:forEach>
</table>