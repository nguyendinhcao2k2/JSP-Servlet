package com.example.assignment_sof3011_caondph20015.viewmodel.errormessage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author caodinh
 */
@Getter
@Setter
@NoArgsConstructor
public class ErrorAcountMessage {

    private String sdtError;
    private String matKhauCuError;
    private String matKhauMoiError;
    private String xacNhanMatKhauError;
    private String errorMessage;
    private String matKhauTaiKhaonError;

}
