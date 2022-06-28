<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Upvex - Responsive Admin Dashboard Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- App favicon -->
    <link rel="shortcut icon" href="assets/images/favicon.ico">

    <!-- plugin css -->
    <link href="assets/libs/jquery-vectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css">

    <!-- App css -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/icons.min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/app.min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/css-my-style.css" rel="stylesheet" type="text/css">

</head>

<body>

<!-- Begin page -->
<div id="wrapper">

    <!-- Topbar Start -->
    <jsp:include page="../shared/navbar-custom.jsp"></jsp:include>
    <!-- end Topbar -->

    <!-- ========== Left Sidebar Start ========== -->
    <jsp:include page="../shared/left-side-menu.jsp"></jsp:include>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">

                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Upvex</a></li>
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Dashboards</a></li>
                                    <li class="breadcrumb-item active">Dashboard</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Well Come Create Product</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <a class="btn btn-primary waves-effect waves-light" href="/product" style="width: 200px;">List Product</a>
                                    </div>
                                    <div class="col-lg-6">

                                    </div>
                                </div>
                                <form method="post" enctype="multipart/form-data">
                                    <c:choose>
                                        <c:when test="${requestScope['productFind'] != null}">
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label for="inputEmail4" class="col-form-label">Title</label>
                                                    <input type="text" required class="form-control" id="inputEmail4" placeholder="Title" name="title" aria-required="true" value="${requestScope['productFind'].getTitle()}">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="inputPassword4" class="col-form-label">Price</label>
                                                    <input type="number" required class="form-control" id="inputPassword4" placeholder="Price" name="price" min="0" aria-required="true" value="${requestScope['productFind'].getPrice()}">
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label for="FullName" class="col-form-label">Quantity</label>
                                                    <input type="number" required class="form-control" id="FullName" placeholder="Quantity" name="quantity" min="0" aria-required="true" value="${requestScope['productFind'].getQuantity()}">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="inputState" class="col-form-label">Category</label>
                                                    <select id="inputState" class="form-control" name="category_id">
                                                        <c:forEach items="${requestScope['categoryList']}" var="cate">
                                                            <c:choose>
                                                                <c:when test="${requestScope['productFind'].getCategoryId() == cate.getId()}">
                                                                    ...<option value="${requestScope['productFind'].getCategoryId()}" selected>${cate.getTitle()}</option>
                                                                </c:when>
                                                                <c:when test="${requestScope['productFind'].getCategoryId() != cate.getId()}">
                                                                    ...<option value="${cate.getId()}">${cate.getTitle()}</option>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label for="example-fileinput" class="col-form-label">File Image</label>
                                                    <input type="file" id="example-fileinput" class="form-control-file" name="file" accept="image/*">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <input type="hidden" name="id_product" value="${requestScope['productFind'].getId()}">
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-primary waves-effect waves-light">Edit</button>
                                        </c:when>
                                        <c:when test="${requestScope['productFind'] == null}">
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label for="inputEmail" class="col-form-label">Title</label>
                                                    <input type="text" required class="form-control" id="inputEmail" placeholder="Title" name="title" aria-required="true" disabled>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="inputPassword" class="col-form-label">Price</label>
                                                    <input type="number" required class="form-control" id="inputPassword" placeholder="Price" name="price" min="0" aria-required="true" disabled>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label for="FullName1" class="col-form-label">Quantity</label>
                                                    <input type="number" required class="form-control" id="FullName1" placeholder="Quantity" name="quantity" min="0" aria-required="true" disabled>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="inputState1" class="col-form-label">Category</label>
                                                    <select id="inputState1" class="form-control" name="category_id" disabled>
                                                        <c:forEach items="${requestScope['categoryList']}" var="item1">
                                                            <option value="${item1.getId()}">${item1.getTitle()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label for="example-fileinput1" class="col-form-label">File Image</label>
                                                    <input type="file" id="example-fileinput1" class="form-control-file" name="file" accept="image/*" disabled>
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-primary waves-effect waves-light" disabled>Edit</button>
                                        </c:when>
                                    </c:choose>
                                </form>
                                <ul style="margin-top: 20px;">
                                    <c:forEach items="${requestScope['errors']}" var="err">
                                        <li>${err}</li>
                                    </c:forEach>
                                </ul>
                                <h6>
                                    <c:if test="${requestScope['message'] != null}">
                                        <c:out value="${requestScope['message']}">

                                        </c:out>
                                    </c:if>
                                </h6>
                            </div> <!-- end card-body -->
                        </div> <!-- end card-->
                    </div> <!-- end col -->
                </div>

            </div> <!-- container -->

        </div> <!-- content -->

        <jsp:include page="../shared/footer.jsp"></jsp:include>

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->


</div>
<!-- END wrapper -->

<!-- Right Sidebar -->
<jsp:include page="../shared/right-bar.jsp"></jsp:include>
<!-- /Right-bar -->

<!-- Right bar overlay-->
<div class="rightbar-overlay"></div>

<jsp:include page="../shared/script.jsp"></jsp:include>

</body>
</html>
