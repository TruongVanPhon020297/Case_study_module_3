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
                            <h4 class="page-title">Well Come Product Dashboard !!! </h4>
                        </div>
                    </div>
                </div>
                <!-- end page title -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card-box">
                            <div class="row" style="margin-bottom: 20px">
                                <div class="col-lg-6">
                                    <c:choose>
                                        <c:when test="${requestScope['message'] == null}">
                                            <a class="btn btn-primary waves-effect waves-light" href="/product?action=create" style="width: 200px;">Create Product</a>
                                        </c:when>
                                        <c:when test="${requestScope['message'] != null}">
                                            <a class="btn btn-primary waves-effect waves-light" href="/product" style="width: 200px;">List Product</a>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <div class="col-lg-6">

                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <h4 class="header-title">List User</h4>
                                </div>
                                <div class="col-lg-6 d-none d-sm-block" style="margin-bottom: 20px">
                                    <form class="app-search">
                                        <div class="app-search-box">
                                            <div class="input-group">
                                                <input type="text" class="form-control" required name="key_search" placeholder="Search...">
                                                <div class="input-group-append">
                                                    <button class="btn" type="submit">
                                                        <i class="fe-search"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-striped mb-0">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Title</th>
                                        <th>Image</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Created</th>
                                        <th>Updated At</th>
                                        <th>Status</th>
                                        <th>Category</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope['productList']}" var="item">
                                        <tr id="tr_${item.getId()}">
                                            <th scope="row">${item.getId()}</th>
                                            <td>${item.getTitle()}</td>
                                            <td>
                                                <img style="width: 50px;height: 42px;" src="/images/${item.getUrlImage()}">
                                            </td>
                                            <td>${item.getPrice()}</td>
                                            <td>${item.getQuantity()}</td>
                                            <td>${item.getCreated_at()}</td>
                                            <td>${item.getUpdated_at()}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.getStopSelling() == 0}">
                                                        ${"Selling"}
                                                    </c:when>
                                                    <c:when test="${item.getStopSelling() == 1}">
                                                        ${"Stop"}
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:forEach var="item1" items="${requestScope['categoryList']}">
                                                    <c:if test="${item.getIdCategory() == item1.getId()}">
                                                        <c:out value="${item1.getTitle()}"></c:out>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <c:choose>
                                                <c:when test="${item.getStopSelling() == 1}">
                                                    <td>
                                                        <a href="javascript: void(0);" onclick="active(${item.getId()})" style="color: #00ff80">Active</a>
                                                    </td>
                                                    <td>
                                                        <p style="color: #dc3545;text-decoration: line-through;">Edit</p>
                                                    </td>
                                                </c:when>
                                                <c:when test="${item.getStopSelling() == 0}">
                                                    <td>
                                                        <a class="" href="javascript: void(0);" onclick="active(${item.getId()})"  style="color: #dc3545">Block</a>
                                                    </td>
                                                    <td>
                                                        <a class="" href="/product?action=edit&id=${item.getId()}" style="color: #00ff80">Edit</a>
                                                    </td>
                                                </c:when>
                                            </c:choose>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <c:if test="${requestScope['error'] == null && requestScope['message'] == null}">
                                    <ul class="pagination pagination-sm" style="margin-top: 20px">
                                        <c:if test="${currentPage != 1}">
                                            <li class="page-item"><a class="page-link" href="/product?page=${currentPage - 1}">Previous</a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${noOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    <li class="page-item page-link" style="background: #00ff80">${i}</li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="/product?page=${i}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${currentPage lt noOfPages}">
                                            <li class="page-item"><a class="page-link" href="/product?page=${currentPage + 1}">Next</a></li>
                                        </c:if>
                                    </ul>
                                </c:if>
                                <h6>
                                   <c:if test="${requestScope['error'] != null}">
                                       <a class="btn btn-primary waves-effect waves-light" href="/product" style="width: 200px;">List Product</a>
                                       <c:forEach items="${requestScope['error']}" var="item">
                                           <div style="margin-top: 20px">
                                               <c:out value="${item}"></c:out>
                                           </div>
                                       </c:forEach>
                                   </c:if>
                                </h6>
                            </div> <!-- end table-responsive-->

                        </div> <!-- end card-box -->
                    </div>
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
<script>
    function active(id) {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                //callback(xmlHttp.responseText);
            {
                    let success =  xmlHttp.responseText;
                    let value = JSON.parse(success);
                    let trChange8 = "#tr_" + value.id + " td:nth-child(8)";
                    let trChange10 = "#tr_" + value.id + " td:nth-child(10)";
                    let trChange11 = "#tr_" + value.id + " td:nth-child(11)";

                    if (value.stopSelling == 1) {
                        document.querySelector(trChange8).innerText = `Stop`;
                        document.querySelector(trChange10).innerHTML = `<a href="javascript: void(0);" onclick="active(\${value.id})" style="color: #00ff80">Active</a>`;
                        document.querySelector(trChange11).innerHTML = `<p style="color: #dc3545;text-decoration: line-through;">Edit</p>`;
                    }else {
                        document.querySelector(trChange8).innerText = `Selling`;
                        document.querySelector(trChange10).innerHTML = `<a class="" href="javascript: void(0);" onclick="active(\${value.id})"  style="color: #dc3545">Block</a>`;
                        document.querySelector(trChange11).innerHTML = `<a class="" href="/product?action=edit&id=\${value.id}" style="color: #00ff80">Edit</a>`;
                    }
            }
        }
        xmlHttp.open("GET", "http://localhost:8080/product?action=block&id=" + id, true); // true for asynchronous
        xmlHttp.send(null);
    }
</script>
</body>
</html>