function detailProduct(id) {
    $.ajax({
        url: "http://localhost:8080/admin/detail?id=" + id,
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $('#tenSP').val(data.tenSP);
            $('#giaBan').val(data.giaBan);
            $('#mo_ta').val(data.moTa);
            $('#soLuong').val(data.soLuongTon);
            $('#mauSac').val(data.mauSac);
            $('#nsx').val(data.nhaSanXuat);
            $('#id_san_pham_update').val(data.id);
        },

        error: function (xhr, textStatus, errorThrown) {
            console.log("Lỗi khi gửi yêu cầu: " + textStatus);
        }
    });
}

$("#form_hoc_ky_update").submit(function (event) {
    event.preventDefault();
    let tenSP = $('#tenSP').val().trim();
    let giaBan = $('#giaBan').val().trim();
    let moTa = $('#mo_ta').val().trim();
    let soLuong = $('#soLuong').val().trim();
    let mauSac = $('#mauSac').val().trim();
    let nsx = $('#nsx').val().trim();
    let id = $('#id_san_pham_update').val().trim();

    let check = true;
    if ($('#image')[0].files[0] === undefined) {
        $('#image_error').text('Vui lòng chọn ảnh');
        check = false;
    }
    if (tenSP === '') {
        $('#tenSP_error').text('Vui lòng nhập tên sản phẩm');
        check = false;
    }

    if (giaBan === '') {
        $('#giaBan_error').text('Vui lòng nhập giá bán');
        check = false;
    } else if (isNaN(giaBan) || parseFloat(giaBan) <= 0) {
        $('#giaBan_error').text('Giá bán phải là số và lớn hơn 0');
        check = false;
    }

    if (moTa === '') {
        $('#mo_ta_error').text('Vui lòng nhập mô tả sản phẩm');
        check = false;
    }
    if (soLuong === '') {
        $('#soLuong_error').text('Vui lòng nhập số lượng');
        check = false;
    } else if (isNaN(soLuong) || parseInt(soLuong) <= 0) {
        $('#soLuong_error').text('Số lượng phải là số và lớn hơn 0');
        check = false;
    }
    if (check) {
        var formData = new FormData();
        formData.append('img', $('#image')[0].files[0]);
        formData.append('tenSP', tenSP);
        formData.append('giaBan', giaBan);
        formData.append('moTa', moTa);
        formData.append('soLuong', soLuong);
        formData.append('mauSac', mauSac);
        formData.append('nsx', nsx);
        formData.append('id', id);

        var xhr = new XMLHttpRequest();

        xhr.open('POST', 'http://localhost:8080/admin/update');
        xhr.onload = function () {
            if (xhr.status === 200) {
                window.location.href = "http://localhost:8080/admin/quan-ly";
            } else {
                console.log('Lỗi trong quá trình gửi dữ liệu');
            }
        };
        xhr.send(formData);
    }
});