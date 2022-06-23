<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                            <h4 class="page-title">Well Come Edit User</h4>
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
                                        <a class="btn btn-primary waves-effect waves-light" href="/users" style="width: 200px;">List User</a>
                                    </div>
                                    <div class="col-lg-6">

                                    </div>
                                </div>
                                <form method="post">
                                    <c:choose>
                                        <c:when test="${requestScope['userFind'] != null}">
                                            <c:forEach items="${requestScope['userFind']}" var="item">
                                                <div class="form-row">
                                                    <div class="form-group col-md-6">
                                                        <label for="inputEmail4" class="col-form-label">Full Name</label>
                                                        <input type="text" value="${item.getFullName()}" name="full_name" required class="form-control" id="inputEmail4" placeholder="Full Name">
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="inputPassword4" class="col-form-label">Email</label>
                                                        <input type="email"  value="${item.getEmail()}" name="email" required class="form-control" id="inputPassword4" placeholder="Email">
                                                    </div>
                                                </div>
                                                <div class="form-row">
                                                    <div class="form-group col-md-6">
                                                        <label for="FullName" class="col-form-label">Mobile</label>
                                                        <input type="text" name="mobile" value="${item.getMobile()}" required class="form-control" id="FullName" placeholder="Mobile">
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label for="Mobile" class="col-form-label">Address</label>
                                                        <input type="text" value="${item.getAddress()}" name="address" required class="form-control" id="Mobile" placeholder="Address">
                                                    </div>
                                                </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <input type="hidden" value="${item.getId()}" name="id_user">
                                                </div>
                                            </div>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${requestScope['userFind'] == null}">
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label for="inputEmail41" class="col-form-label">Full Name</label>
                                                    <input type="text" disabled name="full_name" required class="form-control" id="inputEmail41" placeholder="Full Name">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="inputPassword41" class="col-form-label">Email</label>
                                                    <input type="email" disabled name="email" required class="form-control" id="inputPassword41" placeholder="Email">
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="form-group col-md-6">
                                                    <label for="FullName1" class="col-form-label">Mobile</label>
                                                    <input type="text" disabled name="mobile"  required class="form-control" id="FullName1" placeholder="Mobile">
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <label for="Mobile1" class="col-form-label">Address</label>
                                                    <input type="text" disabled name="address" required class="form-control" id="Mobile1" placeholder="Address">
                                                </div>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                    <button type="submit" class="btn btn-primary waves-effect waves-light">Edit</button>

                                </form>
                                <ul style="margin-top: 20px;">
                                    <c:forEach items="${requestScope['errors']}" var="err">
                                        <li>${err}</li>
                                    </c:forEach>
                                </ul>
                                <h6>
                                    <c:if test="${requestScope['success'] == true}">
                                        ${"Sửa Thông Tin Thành Công"}
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