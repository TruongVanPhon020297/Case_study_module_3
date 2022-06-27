<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="/assets/iziToast/iziToast-1.4.0.min.css">
    <script src="/assets/iziToast/iziToast-1.4.0.js"></script>

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
                            <h4 class="page-title">Wel Come List Order !!! </h4>
                        </div>
                    </div>
                </div>
                <!-- end page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="card-box">
                            <table class="tablesaw table mb-0" data-tablesaw-mode="swipe" data-tablesaw-minimap="">
                                <thead>
                                <tr>
                                    <th scope="col" data-tablesaw-sortable-col="" data-tablesaw-priority="persist">Product Name</th>
                                    <th scope="col" data-tablesaw-sortable-col="" data-tablesaw-priority="2">Price</th>
                                    <th scope="col" data-tablesaw-sortable-col="" data-tablesaw-sortable-default-col="" data-tablesaw-priority="3">Quantity</th>
                                    <th scope="col" data-tablesaw-sortable-col="" data-tablesaw-priority="1">Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope['carts']}" var="item">
                                        <tr>
                                            <td>${item.title}</td>
                                            <td>${item.price}</td>
                                            <td class="text-center">${item.quantity}</td>
                                            <td>${item.totalPrice}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <c:if test="${requestScope['carts'] != null}">
                                <form action="/order?action=pay" method="post">
                                    <button type="submit" class="btn btn-primary waves-effect waves-light" style="margin-top: 50px;width: 100px;">
                                        Pay
                                    </button>
                                </form>
<%--                                <a href="#" class="btn btn-primary waves-effect waves-light" style="margin-top: 50px;width: 100px;">Pay</a>--%>
                            </c:if>

                            <c:if test="${requestScope['success'] == true}">
                                <script>
                                    iziToast.success({
                                        title: 'SUCCESS',
                                        position: 'bottomRight',
                                        timeout: 2500,
                                        message: `<c:out value="${requestScope['message']}"/>`
                                    });
                                </script>
                            </c:if>
                            <c:if test="${requestScope['success'] == false}">
                                <script>
                                    iziToast.error({
                                        title: 'ERROR',
                                        position: 'bottomRight',
                                        timeout: 2500,
                                        message: `<c:out value="${requestScope['message']}"/>`
                                    });
                                </script>
                            </c:if>

                        </div> <!-- end card-box-->
                    </div> <!-- end col-->
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