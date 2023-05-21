const regexSoDienThoai = /(((\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\b/;

function checkOut() {
    var diaChi = document.getElementsByName("diaChia")[0].value;
    var sdt = document.getElementsByName("sdt")[0].value;
    var tenNguoiNhan = document.getElementsByName("tenNguoiNhan")[0].value;
    var check = true;
    if (diaChi.length === 0) {
        $("#dia_chi_error").text("Địa Chỉ Không Được Để Trống");
        check = false;
    }
    if (sdt.length === 0) {
        $("#so_dien_thoai_error").text("Số Điện Thoại Không Được Để Trống");
        check = false;
    } else if (regexSoDienThoai.test(sdt) == false) {
        $("#so_dien_thoai_error").text("Số Điện Thoại Phải Đúng Định Dạng");
        check = false;
    }

    if (tenNguoiNhan.length === 0) {
        $("#ten_nguoi_nhan_error").text("Tên Người Nhận Không Được Để Trống");
        check = false;
    }
    if (check) {
        var xhr = new XMLHttpRequest();
        var url = "http://localhost:8080/cart/check-out-cart";
        var params = "diaChi=" + diaChi + "&sdt=" + sdt + "&tenNguoiNhan=" + tenNguoiNhan;
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseText = xhr.responseText;
                // Xử lý phản hồi từ máy chủ
                alert("Check Out Thành Công Ahihi")
                window.location.href = "http://localhost:8080/trang-chu";
            }
        };
        xhr.send(params);
    } else {
        alert("Check Out Fail");
    }
}

function buyNow(id) {
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/cart/buy-now";
    var params = "id=" + id;
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var responseText = xhr.responseText;
            // Xử lý phản hồi từ máy chủ
            window.location.href = "http://localhost:8080/cart/show-cart";
        }
    };
    xhr.send(params);

}

function addToCart(id) {
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/cart/add-to-cart";
    var params = "id=" + id;
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var responseText = xhr.responseText;
            // Xử lý phản hồi từ máy chủ
            window.location.href = "http://localhost:8080/trang-chu";
        }
    };
    xhr.send(params);

}

function detailCart(id) {
    $.ajax({
        url: "http://localhost:8080/admin/detail-gio-hang?idGH=" + id,
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            var tableBody = $("#productDetailTable");
            tableBody.empty(); // clear current data in table body
            var count = 1;
            for (var i = 0; i < data.length; i++) {
                var row = $("<tr>");
                var tdCount = $("<td>").text(count++);
                var tdName = $("<td>").text(data[i].tenSP);
                var tdQuantity = $("<td>").text(data[i].soLuong);
                var tdTotalPrice = $("<td>").text(data[i].totalPrice);
                var imgSrc = data[i].img;
                var tdImage = $("<td>").append($("<img>").attr("src", "../img/"+imgSrc).attr("width", "50").attr("height", "50"));
                row.append(tdCount, tdName, tdQuantity, tdTotalPrice, tdImage);
                tableBody.append(row);
            }
        },

        error: function (xhr, textStatus, errorThrown) {
            console.log("Lỗi khi gửi yêu cầu: " + textStatus);
        }
    });
}





