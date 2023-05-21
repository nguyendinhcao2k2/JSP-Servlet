<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <c:if test="${sessionScope.user.role =='ADMIN'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown"  role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">Admin Area</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/admin/quan-ly">Product Management Page</a></li>
                            <li>
                                <hr class="dropdown-divider"/>
                            </li>
                            <li><a class="dropdown-item" href="/admin/quan-ly-hoa-don">Order Management Page</a></li>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${sessionScope.user ==null}">
                    <li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
                </c:if>

                <c:if test="${sessionScope.user !=null}">
                    <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                </c:if>

                <c:if test="${sessionScope.user !=null}">
                    <li class="nav-item"><a class="nav-link">Hello : ${sessionScope.user.hoTen}</a></li>
                </c:if>
            </ul>
            <c:if test="${sessionScope.user.role !='ADMIN'}">
                <a href="/cart/show-cart" class="btn btn-outline-dark" type="submit">
                    <i class="bi-cart-fill me-1"></i>
                    Cart
                    <span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.sizeCart}</span>
                </a>
            </c:if>
        </div>
    </div>
</nav>