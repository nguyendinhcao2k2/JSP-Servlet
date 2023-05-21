package com.example.assignment_sof3011_caondph20015.service.impl;

import com.example.assignment_sof3011_caondph20015.entity.Account;
import com.example.assignment_sof3011_caondph20015.service.IAccountService;
import com.example.assignment_sof3011_caondph20015.service.ILoginService;
import com.example.assignment_sof3011_caondph20015.util.GoogleUtils;
import com.example.assignment_sof3011_caondph20015.viewmodel.QLAccount;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.QLRegisterError;
import com.example.assignment_sof3011_caondph20015.viewmodel.request.AccountRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author caodinh
 */
public class LoginServiceImpl implements ILoginService {

    private IAccountService accountService = new AccountServiceImpl();

    @Override
    public void loginGoogle(String code, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (code == null || code.isEmpty()) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            String accessToken = GoogleUtils.getToken(code);
            AccountRequest accountRequest = GoogleUtils.getUserInfo(accessToken);
            Account account = accountService.getOneByEmail(accountRequest.getEmail());
            if (account == null) {
                account = new Account();
                String[] parts = accountRequest.getEmail().split("@");
                account.setHoTen(parts[0]);
                account.setMa(accountService.genMaAccount());
                account.setRole("USER");
                account.setEmail(accountRequest.getEmail());
                if (accountService.saveAccountByLoginGoogle(account)) {
                    session.setAttribute("user", account);
                    response.sendRedirect("/trang-chu");
                }
            } else {
                session.setAttribute("user", account);
                response.sendRedirect("/trang-chu");
            }
        }
    }

    @Override
    public void dangKy(HttpServletRequest request, HttpServletResponse response, QLAccount qlAccount, QLRegisterError qlRegisterError) throws IOException, ServletException {
        if (accountService.saveOrUpdate(qlAccount, qlRegisterError)) {
            response.sendRedirect("/login");
        } else {
            request.setAttribute("error", qlRegisterError);
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
        }
    }

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        String sdt = request.getParameter("sdt");
        String pass = request.getParameter("password");
        Account account = null;
        try {
            account = accountService.getOneBySDTAndPassord(sdt, pass);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
        if (account != null) {
            HttpSession ss = request.getSession();
            ss.setAttribute("user", account);
            response.setContentType("text/plain");
            request.setCharacterEncoding("UTF-8");
            response.getWriter().write("Data saved successfully!");
        } else {
            response.sendError(400, "Invalid user or password");
        }
    }

}
