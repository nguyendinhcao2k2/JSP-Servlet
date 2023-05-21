package com.example.assignment_sof3011_caondph20015.service;

import com.example.assignment_sof3011_caondph20015.viewmodel.QLAccount;
import com.example.assignment_sof3011_caondph20015.viewmodel.errormessage.QLRegisterError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author caodinh
 */
public interface ILoginService {

    void loginGoogle(String code, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void dangKy(HttpServletRequest request, HttpServletResponse response, QLAccount qlAccount, QLRegisterError qlRegisterError ) throws IOException, ServletException;

    void login(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
