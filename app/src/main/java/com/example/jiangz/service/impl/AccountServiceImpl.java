package com.example.jiangz.service.impl;

import android.content.Context;
import com.example.jiangz.dao.AccountDBAdapter;
import com.example.jiangz.entity.Account;
import com.example.jiangz.service.AccountService;

import org.androidannotations.annotations.EBean;

import java.util.List;
import java.util.Map;

/**
 * Created by JiangZ on 2015-09-05.
 */
@EBean
public class AccountServiceImpl implements AccountService {

    private AccountDBAdapter accountDBAdapter;

    public AccountServiceImpl(Context context){
          accountDBAdapter = new AccountDBAdapter(context);
    }

    @Override
    public long createAccount(Account account) {
        long result = 0l;

        try {
            accountDBAdapter.open("write");
            result = accountDBAdapter.createAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            accountDBAdapter.closeDB();
            accountDBAdapter.closeHelper();
        }

        return result;
    }

    @Override
    public boolean deleteAllAccounts() {
        boolean flag = false;
        try {
            accountDBAdapter.open("write");
            flag = accountDBAdapter.deleteAllAccounts();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            accountDBAdapter.closeDB();
            accountDBAdapter.closeHelper();
        }
        return flag;
    }

    @Override
    public int deleteAccountById(Integer id) {
        int result = 0;

        try{
            accountDBAdapter.open("write");
            result = accountDBAdapter.deleteAccountById(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            accountDBAdapter.closeDB();
            accountDBAdapter.closeHelper();
        }

        return result;
    }

    @Override
    public int update(Account account) {
        int result = 0;

        try{
            accountDBAdapter.open("write");
            result = accountDBAdapter.update(account);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            accountDBAdapter.closeDB();
            accountDBAdapter.closeHelper();
        }

        return result;
    }

    @Override
    public List<Account> fetchAccountByMap(Map<Object, Object> map) {

        List<Account> accounts = null;

        try {
            accountDBAdapter.open("write");
            accounts = accountDBAdapter.fetchAccountByMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            accountDBAdapter.closeDB();
            accountDBAdapter.closeHelper();
        }
        return accounts;
    }
}
