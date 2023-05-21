<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/10/2023
  Time: 7:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chang Password</title>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/custom/styles.css" rel="stylesheet"/>
    <!-- includes Bootstrap-->
    <link href="../css/plugin/bootstrap.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../css/custom/login.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="/trang-chu">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="/san-pham">Product Page</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card border-0 shadow rounded-3 my-5">
                <div class="card-body p-4 p-sm-5">
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Change Password</h5>
                    <form action="/doi-mat-khau" method="post">
                        <div class="form-floating mb-3">
                            <input type="text" name="sdt" class="form-control" id="floatingInput"
                                   placeholder="name@example.com">
                            <label for="floatingInput">Số Điện Thoại</label>
                            <c:if test="${error.sdtError != null}">
                                <p style="color: red">${error.sdtError}</p>
                            </c:if>

                            <c:if test="${error.matKhauTaiKhaonError != null}">
                                <p style="color: red">${error.matKhauTaiKhaonError}</p>
                            </c:if>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="password" name="matKhauCu" class="form-control" id="floatingPassword"
                                   placeholder="Password">
                            <label for="floatingPassword">OldPassword</label>
                            <c:if test="${error.matKhauCuError != null}">
                                <p style="color: red">${error.matKhauCuError}</p>
                            </c:if>

                            <c:if test="${error.matKhauTaiKhaonError != null}">
                                <p style="color: red">${error.matKhauTaiKhaonError}</p>
                            </c:if>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="password" name="matKhau" class="form-control" id="floatingNewPassword"
                                   placeholder="Password">
                            <label for="floatingNewPassword">New PassWord</label>
                            <c:if test="${error.matKhauMoiError != null}">
                                <p style="color: red">${error.matKhauMoiError}</p>
                            </c:if>

                            <c:if test="${error.errorMessage != null}">
                                <p style="color: red">${error.errorMessage}</p>
                            </c:if>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="password" name="xacNhanMatKhau" class="form-control" id="floatingRePassword"
                                   placeholder="Password">
                            <label for="floatingRePassword">Confirm Password</label>
                            <c:if test="${error.xacNhanMatKhauError != null}">
                                <p style="color: red">${error.xacNhanMatKhauError}</p>
                            </c:if>

                            <c:if test="${error.errorMessage != null}">
                                <p style="color: red">${error.errorMessage}</p>
                            </c:if>
                        </div>

                        <div class="d-grid">
                            <button class="btn btn-primary btn-login text-uppercase fw-bold" type="submit">Change
                                Password
                            </button>
                        </div>
                        <hr class="my-4">
                        <a class="d-block text-center mt-2 small" href="/login">Sign In</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../layout/footer.jsp"/>
<!-- Bootstrap core JS-->
<script src="../js/plugin/bootstrap.bundle.min.js"></script>
</body>
</html>
