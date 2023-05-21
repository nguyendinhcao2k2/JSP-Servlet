package com.example.assignment_sof3011_caondph20015.repository;

import com.example.assignment_sof3011_caondph20015.entity.Account;

/**
 * @author caodinh
 */
public interface IAccountRepository {

    boolean saveAccountByLoginGoogle(Account account);

    Account getOneByEmail(String email);

    Account getOneBySDTAndPassword(String sdt, String password);

    String genMaAccount();

    boolean saveOrUpdate(Account account);

    Account getOneBySDT(String sdt);

}
