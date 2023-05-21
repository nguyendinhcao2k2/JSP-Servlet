package com.example.assignment_sof3011_caondph20015.viewmodel.errormessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author caodinh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QLRegisterError {

    private String hoTenError;
    private String matKhauError;
    private String xacNhanMatKhauError;
    private String sdtError;
    private String error;

}
