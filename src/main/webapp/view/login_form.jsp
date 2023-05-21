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
    <title>Title</title>
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
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Sign In</h5>
                    <div class="form-floating mb-3">
                        <input type="text" name="sdt" class="form-control" id="floatingInput"
                               placeholder="name@example.com">
                        <label for="floatingInput">Số Điện Thoại</label>
                    </div>
                    <span class="text-danger"
                          id="sdt_error"></span>

                    <div class="form-floating mb-3">
                        <input type="password" name="password" class="form-control" id="floatingPassword"
                               placeholder="Password">
                        <label for="floatingPassword">Password</label>
                    </div>
                    <span class="text-danger"
                          id="password_error"></span>

                    <div class="d-grid">
                        <button class="btn btn-primary btn-login text-uppercase fw-bold" onclick="dangNhap()"
                                type="submit">Login
                        </button>
                    </div>
                    <hr>
                    <div class="d-grid">
                        <a class="d-block text-center mt-2 small" href="/register">Don't Account Register Here !</a>
                    </div>
                    <hr class="my-4">
                    <div class="d-grid">
                        <a class="d-block text-center mt-2 small" href="/change-pass">Change Password</a>
                    </div>
                    <hr class="my-4">
                    <div class="d-grid mb-2">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/google&response_type=code&client_id=95721316114-11cak60icnhtqian7m7aatjbosr4ig3s.apps.googleusercontent.com&approval_prompt=force"
                           class="btn btn-google btn-login text-uppercase fw-bold" type="submit">
                            <i class="fab fa-google me-2"></i> Sign in with Google
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../layout/footer.jsp"/>
<!-- Bootstrap core JS-->
<!-- Bootstrap core JS-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script src="../js/custom/login.js"></script>
</body>
</html>
