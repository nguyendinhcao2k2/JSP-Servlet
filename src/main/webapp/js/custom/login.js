const regexSoDienThoai = /(((\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\b/;

function dangNhap() {
    let sdt = $('input[name="sdt"]').val().trim();
    let password = $('input[name="password"]').val().trim();
    let check = true;

    if (sdt.length == 0) {
        $("#sdt_error").text("Số Điện Thoại Không Được Để Trống");
        check = false;
    } else if (regexSoDienThoai.test(sdt) == false) {
        $("#sdt_error").text("Số Điện Thoại Phải Định Dạng");
        check = false;
    } else {
        $("#sdt_error").text("");
    }

    if (password.length == 0) {
        $("#password_error").text("Mật Khẩu Không Được Để Trống");
        check = false;
    } else if (password.length < 6) {
        $("#password_error").text("Mật Khẩu Phải trên 5 ký tự");
        check = false;
    } else {
        $("#password_error").text("");
    }
    if (check) {
        var xhr = new XMLHttpRequest();
        var url = "http://localhost:8080/dang-nhap";
        var params = "sdt=" + sdt + "&password=" + password;
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var responseText = xhr.responseText;
                // Xử lý phản hồi từ máy chủ
                $("#sdt_error").text("");
                $("#password_error").text("");
                window.location.href = "http://localhost:8080/trang-chu";
            } else {
                $("#sdt_error").text("Invalid user or Password");
                $("#password_error").text("Invalid user or Password");
            }
        };
        xhr.send(params);
    } else {
        alert("Login Fail");
    }
}
