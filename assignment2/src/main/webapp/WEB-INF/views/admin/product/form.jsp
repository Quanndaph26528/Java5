<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>

<div class="container mt-3">
<form:form action="/admin/product/create"  modelAttribute="item">

    <div class="row">
        <div class="col-4">Id sản phẩm:</div>
        <div class="col-8" style="color: red">
            <form:input path="id" class="form-control" type="text"
                        aria-label="default input example"  readonly="true"/>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-4">Tên sản phẩm:</div>
        <div class="col-8" style="color: red">
            <form:input path="name" class="form-control" type="text"
                        aria-label="default input example"/>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-4">Price sản phẩm:</div>
        <div class="col-8" style="color: red">
            <form:input path="price" class="form-control" type="text"
                        aria-label="default input example"/>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-4">Loại sản phẩm:</div>
        <div class="col-8">
            <form:select path="Category.id">
                <form:options items="${listCategory}" itemLabel="name" itemValue="id"></form:options>
                <c:forEach items="${listCategory1}" var="ca">
                    <form:option value="${ca.id}">${ca.name}</form:option>
                </c:forEach>
            </form:select>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-4">Img sản phẩm:</div>
        <div class="col-8" style="color: red">
            <form:input path="image" class="form-control" type="text"
                        aria-label="default input example"/>
        </div>
    </div>



        <button  class="btn btn-primary">Add</button>
        <button  class="btn btn-primary" formaction="/admin/product/update">Update</button>
</form:form>
</div>
