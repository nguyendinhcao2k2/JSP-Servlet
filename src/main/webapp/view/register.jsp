<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/11/2023
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="style.css">
    <!-- includes Bootstrap-->
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="../css/plugin/bootstrap.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../css/custom/register.css">
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
        <div class="col-lg-10 col-xl-9 mx-auto">
            <div class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">
                <div class="card-img-left d-none d-md-flex">
                    <!-- Background image for card set in CSS! -->
                </div>
                <div class="card-body p-4 p-sm-5">
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Register</h5>
                    <form action="/dang-ky" method="post">
                        <div class="form-floating mb-3">
                            <input type="text" name="hoTen" class="form-control" id="floatingInputHoTen"
                                   placeholder="Nguyễn Văn A">
                            <label for="floatingInputHoTen">Họ Tên</label>
                            <span class="text-danger"
                                  id="hoTen_error">${error.hoTenError}</span>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="text" name="sdt" class="form-control" id="floatingInputSDT"
                                   placeholder="Số Điện Thoại" required autofocus>
                            <label for="floatingInputSDT">Số Điện Thoại</label>
                            <span class="text-danger"
                                  id="sdt_error">${error.sdtError}</span>
                        </div>
                        <hr>

                        <div class="form-floating mb-3">
                            <input type="password" name="matKhau" class="form-control" id="floatingPassword"
                                   placeholder="Password">
                            <label for="floatingPassword">Password</label>
                            <span class="text-danger"
                                  id="password_error">${error.matKhauError}</span>
                            <span class="text-danger"
                            >${error.error}</span>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="password" name="xacNhanMatKhau" class="form-control"
                                   id="floatingPasswordConfirm" placeholder="Confirm Password">
                            <label for="floatingPasswordConfirm">Confirm Password</label>
                            <span class="text-danger"
                                  id="rePassword_error">${error.xacNhanMatKhauError}</span>
                            <span class="text-danger"
                            >${error.error}</span>
                        </div>

                        <div class="d-grid mb-2">
                            <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">
                                Register
                            </button>
                        </div>
                    </form>
                    <a class="d-block text-center mt-2 small" href="/login">Have an account? Sign In</a>

                    <hr class="my-4">

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../layout/footer.jsp"/>
<!-- Bootstrap core JS-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!-- Core theme JS-->
<script src="../js/custom/scripts.js"></script>
<script src="../js/custom/login.js"></script>
</body>
</html>
