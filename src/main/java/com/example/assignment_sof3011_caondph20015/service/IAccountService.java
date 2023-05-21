package com.example.assignment_sof3011_caondph20015.service;

import com.example.assignment_sof3011_caondph20015.entity.Account;
import com.example.assignment_sof3011_caondph20015.viewmodel.QLAccount;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.ErrorAcountMessage;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.QLRegisterError;

/**
 * @author caodinh
 */
public interface IAccountService {
    boolean saveAccountByLoginGoogle(Account account);
    String genMaAccount();
    Account getOneByEmail(String email);
    Account getOneBySDTAndPassord(String sdt,String password);

    boolean saveOrUpdate(QLAccount qlAccount, QLRegisterError qlRegisterError);

    boolean update(String sdt, String matKhauCu, String matKhauMoi, String xacNhanMatKhau, ErrorAcountMessage errorAcountMessage);

}
