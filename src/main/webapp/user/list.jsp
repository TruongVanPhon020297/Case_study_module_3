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
                            <h4 class="page-title">Well Come User Dashboard !!! </h4>
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
                                            <a class="btn btn-primary waves-effect waves-light" href="/users?action=create" style="width: 200px;">Create User</a>
                                        </c:when>
                                        <c:when test="${requestScope['message'] != null}">
                                            <a class="btn btn-primary waves-effect waves-light" href="/users" style="width: 200px;">List User</a>
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
                                        <th>Full Name</th>
                                        <th>Image</th>
                                        <th>Email</th>
                                        <th>Mobile</th>
                                        <th>Role</th>
                                        <th>Status</th>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope['userList']}" var="item">
                                        <tr id="tr_${item.getId()}">
                                            <th>${item.getId()}</th>
                                            <td>${item.getFullName()}</td>
                                            <td>
                                                <img style="width: 50px;height: 42px;" src="/images/${item.getUrlImage()}">
                                            </td>
                                            <td>${item.getEmail()}</td>
                                            <td>${item.getMobile()}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.getAdmin() == 0}">
                                                        ${"User"}
                                                    </c:when>
                                                    <c:when test="${item.getAdmin() == 1}">
                                                        ${"Admin"}
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${item.getStatus() == 0}">
                                                        ${"Block"}
                                                    </c:when>
                                                    <c:when test="${item.getStatus() == 1}">
                                                        ${"Active"}
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <c:choose>
                                                <c:when test="${item.getStatus() == 0}">
                                                    <td>
                                                        <a href="javascript: void(0);" onclick="active(${item.getId()})" style="color: #00ff80">Active</a>
                                                    </td>
                                                    <td>
                                                        <p style="color: #dc3545;text-decoration: line-through;">Edit</p>
                                                    </td>
                                                </c:when>
                                                <c:when test="${item.getStatus() == 1}">
                                                    <td>
                                                        <a class="" href="javascript: void(0);" onclick="active(${item.getId()})"  style="color: #dc3545">Block</a>
                                                    </td>
                                                    <td>
                                                        <a class="" href="/users?action=edit&id=${item.getId()}" style="color: #00ff80">Edit</a>
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
                                            <li class="page-item"><a class="page-link" href="/users?page=${currentPage - 1}">Previous</a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${noOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    <li class="page-item page-link" style="background: #00ff80;color: red">${i}</li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="/users?page=${i}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${currentPage lt noOfPages}">
                                            <li class="page-item"><a class="page-link" href="/users?page=${currentPage + 1}">Next</a></li>
                                        </c:if>
                                    </ul>
                                </c:if>
                                <h6>
                                    <c:if test="${requestScope['error'] != null}">
                                        <a class="btn btn-primary waves-effect waves-light" href="/users" style="width: 200px;">List User</a>
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
    function active(id){
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
                //callback(xmlHttp.responseText);
            {
                let success =  xmlHttp.responseText;
                let value = JSON.parse(success);
                let trChange7 = "#tr_" + value.id + " td:nth-child(7)";
                let trChange8 = "#tr_" + value.id + " td:nth-child(8)";
                let trChange9 = "#tr_" + value.id + " td:nth-child(9)";

                if (value.status == 0) {
                    document.querySelector(trChange7).innerText = `Block`;
                    document.querySelector(trChange8).innerHTML = `<a href="javascript: void(0);" onclick="active(\${value.id})" style="color: #00ff80">Active</a>`;
                    document.querySelector(trChange9).innerHTML = `<p style="color: #dc3545;text-decoration: line-through;">Edit</p>`;
                }else {
                    document.querySelector(trChange7).innerText = `Active`;
                    document.querySelector(trChange8).innerHTML = `<a class="" href="javascript: void(0);" onclick="active(\${value.id})"  style="color: #dc3545">Block</a>`;
                    document.querySelector(trChange9).innerHTML = `<a class="" href="/users?action=edit&id=\${value.id}" style="color: #00ff80">Edit</a>`;
                }

            }
        }
        xmlHttp.open("GET", "http://localhost:8080/users?action=block&id=" + id, true); // true for asynchronous
        xmlHttp.send(null);
    }
</script>

</body>
</html>