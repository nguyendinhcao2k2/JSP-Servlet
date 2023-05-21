<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 3/12/2023
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart Page</title>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/custom/styles.css" rel="stylesheet"/>
    <!-- includes Bootstrap-->
    <link href="../css/plugin/bootstrap.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../css/custom/cart.css">
</head>
<jsp:include page="../layout/header.jsp"/>
<br>
<header class="bg-dark py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder">Trang Giỏ Hàng</h1>
            <p class="lead fw-normal text-white-50 mb-0">Uy Tín Đặt Lên Hàng Đầu</p>
        </div>
    </div>
</header>
<br>
<div class="container pb-5 mt-n2 mt-md-n3">
    <div class="row">
        <div class="col-xl-9 col-md-8">
            <h2 class="h6 d-flex flex-wrap justify-content-between align-items-center px-4 py-3 bg-secondary"><span>Products</span><a
                    class="font-size-sm" href="/trang-chu">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                     class="feather feather-chevron-left" style="width: 1rem; height: 1rem;">
                    <polyline points="15 18 9 12 15 6"></polyline>
                </svg>
                Continue shopping</a></h2>
            <!-- Item-->
            <c:choose>
                <c:when test="${empty gh.chiTietGioHang}">
                    <h1 class="display-4">Cart is Empty</h1>
                </c:when>
                <c:otherwise>
                    <c:if test="${ghList !=null}">
                        <c:forEach items="${ghList}" var="l">
                            <div class="d-sm-flex justify-content-between my-4 pb-4 border-bottom">
                                <div class="media d-block d-sm-flex text-center text-sm-left">
                                    <a class="cart-item-thumb mx-auto mr-sm-4"
                                       href="/chi-tiet?id=${l.chiTietSP.id}"><img
                                            src="../img/${l.chiTietSP.img}" alt="Product"></a>
                                    <div class="media-body pt-3">
                                        <h3 class="product-card-title font-weight-semibold border-0 pb-0"><a
                                                href="/chi-tiet?id=${l.chiTietSP.id}">${l.chiTietSP.tenSP}</a>
                                        </h3>
                                        <div class="font-size-sm"><span
                                                class="text-muted mr-2">Color:</span>${l.chiTietSP.mauSac.ten}</div>
                                        <div class="font-size-lg text-primary pt-2">Price: ${l.chiTietSP.giaBan}$</div>
                                    </div>
                                </div>
                                <div class="pt-2 pt-sm-0 pl-sm-3 mx-auto mx-sm-0 text-center text-sm-left"
                                     style="max-width: 10rem;">
                                    <form action="/cart/update-cart?id=${l.chiTietSP.id}" method="post">
                                        <div class="form-group mb-2">
                                            <label for="quantity1">Quantity</label>
                                            <input class="form-control form-control-sm" type="number" name="soLuong"
                                                   id="quantity1"
                                                   value="${l.soLuong}">
                                        </div>
                                        <span class="text-danger"
                                              id="so_luong_error"></span>
                                        <button class="btn btn-outline-secondary btn-sm btn-block mb-2" type="submit">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                                 viewBox="0 0 24 24"
                                                 fill="none"
                                                 stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                                 stroke-linejoin="round"
                                                 class="feather feather-refresh-cw mr-1">
                                                <polyline points="23 4 23 10 17 10"></polyline>
                                                <polyline points="1 20 1 14 7 14"></polyline>
                                                <path d="M3.51 9a9 9 0 0 1 14.85-3.36L23 10M1 14l4.64 4.36A9 9 0 0 0 20.49 15"></path>
                                            </svg>
                                            Update cart
                                        </button>
                                    </form>
                                    <a class="btn btn-outline-danger btn-sm btn-block mb-2"
                                       href="/cart/remove-cart?idCart=${l.chiTietSP.id}" type="button">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                             viewBox="0 0 24 24"
                                             fill="none"
                                             stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                             stroke-linejoin="round"
                                             class="feather feather-trash-2 mr-1">
                                            <polyline points="3 6 5 6 21 6"></polyline>
                                            <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                                            <line x1="10" y1="11" x2="10" y2="17"></line>
                                            <line x1="14" y1="11" x2="14" y2="17"></line>
                                        </svg>
                                        Remove
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${ghList ==null}">
                        <h1 class="display-4">Cart is Empty</h1>
                    </c:if>
                </c:otherwise>
            </c:choose>

        </div>
        <!-- Sidebar-->
        <div class="col-xl-3 col-md-4 pt-3 pt-md-0">
            <h2 class="h6 px-4 py-3 bg-secondary text-center">Subtotal</h2>
            <div class="h3 font-weight-semibold text-center py-3">${totalPrice}$</div>
            <hr>
            <h3 class="h6 pt-4 font-weight-semibold"><span class="badge badge-success mr-2">Note</span>Additional
                comments</h3>
            <textarea class="form-control mb-3" id="order-comments" rows="5"></textarea>
            <c:if test="${sessionScope.sizeCart > 0}">
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                         class="feather feather-credit-card mr-2">
                        <rect x="1" y="4" width="22" height="16" rx="2" ry="2"></rect>
                        <line x1="1" y1="10" x2="23" y2="10"></line>
                    </svg>
                    CheckOut
                </button>
            </c:if>
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Nơi Nhận Hàng</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="input-group mb-3">
                                <span class="input-group-text">Địa Chỉ</span>
                                <input type="text" name="diaChia" class="form-control" aria-label="Sizing example input"
                                       aria-describedby="inputGroup-sizing-default">
                            </div>
                            <span class="text-danger"
                                  id="dia_chi_error"></span>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Số Điện Thoại</span>
                                <input type="text" name="sdt" class="form-control" aria-label="Sizing example input"
                                       aria-describedby="inputGroup-sizing-default">
                            </div>
                            <span class="text-danger"
                                  id="so_dien_thoai_error"></span>

                            <div class="input-group mb-3">
                                <span class="input-group-text">Tên Người Nhận</span>
                                <input type="text" name="tenNguoiNhan" class="form-control"
                                       aria-label="Sizing example input"
                                       aria-describedby="inputGroup-sizing-default">
                            </div>
                            <span class="text-danger"
                                  id="ten_nguoi_nhan_error"></span>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" onclick="checkOut()" class="btn btn-primary">CheckOut</button>
                        </div>
                    </div>
                </div>
            </div>
            <%--End Modal--%>
        </div>
    </div>
</div>
<jsp:include page="../layout/footer.jsp"/>
<!-- Bootstrap core JS-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script src="../js/custom/cart.js"></script>
</body>
</html>
