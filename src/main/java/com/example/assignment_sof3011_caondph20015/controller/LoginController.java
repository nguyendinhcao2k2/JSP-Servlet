package com.example.assignment_sof3011_caondph20015.controller; /**
 * @author caodinh
 */

import com.example.assignment_sof3011_caondph20015.service.IAccountService;
import com.example.assignment_sof3011_caondph20015.service.ILoginService;
import com.example.assignment_sof3011_caondph20015.service.impl.AccountServiceImpl;
import com.example.assignment_sof3011_caondph20015.service.impl.LoginServiceImpl;
import com.example.assignment_sof3011_caondph20015.viewmodel.QLAccount;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.ErrorAcountMessage;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.QLRegisterError;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "LoginController", value = {
        "/login",
        "/dang-nhap",
        "/logout",
        "/register",
        "/dang-ky",
        "/change-pass",
        "/doi-mat-khau",
        "/google"
})
public class LoginController extends HttpServlet {

    private IAccountService accountService = new AccountServiceImpl();

    private ILoginService loginService = new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("logout")) {
            logout(request, response);
        } else if (uri.contains("register")) {
            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
        } else if (uri.contains("login")) {
            request.getRequestDispatcher("/view/login_form.jsp").forward(request, response);
        } else if (uri.contains("change-pass")) {
            request.getRequestDispatcher("/view/change_pass.jsp").forward(request, response);
        } else if (uri.contains("google")) {
            loginGoogle(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("dang-nhap")) {
            login(request, response);
        } else if (uri.contains("dang-ky")) {
            try {
                dangKy(request, response);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else if (uri.contains("doi-mat-khau")) {
            changePass(request, response);
        }
    }

    private void loginGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String code = request.getParameter("code");
        loginService.loginGoogle(code, request, response);
    }

    public void dangKy(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException, ServletException {
        QLAccount qlAccount = new QLAccount();
        QLRegisterError qlRegisterError = new QLRegisterError();
        BeanUtils.populate(qlAccount, request.getParameterMap());
        loginService.dangKy(request, response, qlAccount, qlRegisterError);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/login");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginService.login(request, response);
    }

    public void changePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sdt = request.getParameter("sdt");
        String matKhauCu = request.getParameter("matKhauCu");
        String matKhauMoi = request.getParameter("matKhau");
        String xacNhanMatKhau = request.getParameter("xacNhanMatKhau");
        ErrorAcountMessage errorAcountMessage = new ErrorAcountMessage();
        if (accountService.update(sdt, matKhauCu, matKhauMoi, xacNhanMatKhau, errorAcountMessage)) {
            try {
                response.sendRedirect("login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            request.setAttribute("error", errorAcountMessage);
            request.getRequestDispatcher("/view/change_pass.jsp").forward(request, response);
        }
    }
}
