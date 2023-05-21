package com.example.assignment_sof3011_caondph20015.filter; /**
 * @author caodinh
 */


import com.example.assignment_sof3011_caondph20015.entity.Account;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/cart/*")
public class CartFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resq = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("user");
        if (acc != null && acc.getRole().equals("ADMIN")) {
            resq.sendRedirect("/trang-chu");
        } else {
            filterChain.doFilter(req, resq);
        }
    }

}
