package com.example.jiangz.service;

import com.example.jiangz.entity.Account;

import java.util.List;
import java.util.Map;

/**
 * Created by JiangZ on 2015-09-05.
 */
public interface AccountService {
    long createAccount(Account account);

    boolean deleteAllAccounts();

    int deleteAccountById(Integer id);

    int update(Account account);

    List<Account> fetchAccountByMap(Map<Object,Object> map);
}
