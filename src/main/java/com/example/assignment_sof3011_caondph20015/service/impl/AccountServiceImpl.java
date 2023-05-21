package com.example.assignment_sof3011_caondph20015.service.impl;

import com.example.assignment_sof3011_caondph20015.entity.Account;
import com.example.assignment_sof3011_caondph20015.repository.IAccountRepository;
import com.example.assignment_sof3011_caondph20015.repository.impl.AccountRepositoryImpl;
import com.example.assignment_sof3011_caondph20015.service.IAccountService;
import com.example.assignment_sof3011_caondph20015.viewmodel.QLAccount;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.ErrorAcountMessage;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.QLRegisterError;

/**
 * @author caodinh
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountRepository accountRepository = new AccountRepositoryImpl();

    @Override
    public boolean saveAccountByLoginGoogle(Account account) {
        return accountRepository.saveAccountByLoginGoogle(account);
    }

    @Override
    public String genMaAccount() {
        return accountRepository.genMaAccount();
    }

    @Override
    public Account getOneByEmail(String email) {
        return accountRepository.getOneByEmail(email);
    }

    @Override
    public Account getOneBySDTAndPassord(String sdt, String password) {
        return accountRepository.getOneBySDTAndPassword(sdt, password);
    }

    @Override
    public boolean saveOrUpdate(QLAccount qlAccount, QLRegisterError qlRegisterError) {
        boolean check = true;

        if (qlAccount.getHoTen().isEmpty()) {
            qlRegisterError.setHoTenError("Họ Tên Không Được Để Trống");
            check = false;
        }

        if (qlAccount.getMatKhau().isEmpty()) {
            qlRegisterError.setMatKhauError("Mật Không Được Để Trống");
            check = false;
        }

        if (qlAccount.getXacNhanMatKhau().isEmpty()) {
            qlRegisterError.setXacNhanMatKhauError("Nhập Lại Mật Khẩu Không Được Để Trống");
            check = false;
        }
        if (qlAccount.getSdt().isEmpty()) {
            qlRegisterError.setSdtError("Số Điện Thoại Không Được Để Trống");
            check = false;
        } else {
            if (!qlAccount.getSdt().matches("^(\\+84|0)[1-9][0-9]{7,8}$|^(\\+84|0)[1-9][0-9]{2}\\s[0-9]{3}\\s[0-9]{3}$|^(\\+84|0)[1-9][0-9]{2}\\-[0-9]{3}\\-[0-9]{3}$")) {
                qlRegisterError.setSdtError("Số Điện Thoại Phải Đúng Định Dạng");
                check = false;
            } else {
                if (accountRepository.getOneBySDT(qlAccount.getSdt()) != null) {
                    qlRegisterError.setSdtError("Số Điện Thoại Này Đã Được Đăng Ký");
                    check = false;
                }
            }
        }

        if (check == true) {
            if (!qlAccount.getMatKhau().equals(qlAccount.getXacNhanMatKhau())) {
                qlRegisterError.setError("Mật Khẩu và Nhập Lại Mật Khẩu Phải Trùng Nhau");
                return false;
            } else {
                Account account = new Account();
                account.setMa(accountRepository.genMaAccount());
                account.setHoTen(qlAccount.getHoTen());
                account.setSdt(qlAccount.getSdt());
                account.setMatKhau(qlAccount.getMatKhau());
                account.setRole("USER");
                accountRepository.saveOrUpdate(account);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(String sdt, String matKhauCu, String matKhauMoi, String xacNhanMatKhau, ErrorAcountMessage errorAcountMessage) {
        boolean check = true;
        if (sdt.isEmpty()) {
            errorAcountMessage.setSdtError("Không Được Để Trống SDT");
            check = false;
        } else {
            if (!sdt.matches("^(\\+84|0)[1-9][0-9]{7,8}$|^(\\+84|0)[1-9][0-9]{2}\\s[0-9]{3}\\s[0-9]{3}$|^(\\+84|0)[1-9][0-9]{2}\\-[0-9]{3}\\-[0-9]{3}$")) {
                errorAcountMessage.setSdtError("Số Điện Thoại Phải Đúng Định Dạng");
                check = false;
            }
        }

        if (matKhauCu.isEmpty()) {
            errorAcountMessage.setMatKhauCuError("Mật Khẩu Cũ Không Được Để Trống");
            check = false;
        }

        if (matKhauMoi.isEmpty()) {
            errorAcountMessage.setMatKhauMoiError("Mật Khẩu Mới Không Được Để Trống");
            check = false;
        }

        if (xacNhanMatKhau.isEmpty()) {
            errorAcountMessage.setXacNhanMatKhauError("Xác Nhận Mật Khẩu  Không Được Để Trống");
            check = false;
        }
        if (check) {
            Account account = accountRepository.getOneBySDTAndPassword(sdt, matKhauCu);
            if (!matKhauMoi.equals(xacNhanMatKhau)) {
                errorAcountMessage.setErrorMessage("Mật Khẩu Mới Và Xác Nhận Mật Khẩu Phải Trùng Nhau");
                return false;
            }
            if (account != null) {
                account.setMatKhau(matKhauMoi);
                accountRepository.saveOrUpdate(account);
                return true;
            } else {
                errorAcountMessage.setMatKhauTaiKhaonError("Tài Khoản Mật Khảu Không Chính Xác");
                return false;
            }
        }
        return false;
    }

}
