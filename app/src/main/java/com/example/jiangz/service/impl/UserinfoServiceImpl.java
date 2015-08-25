package com.example.jiangz.service.impl;

import android.content.Context;

import com.example.jiangz.dao.UserDBAdapter;
import com.example.jiangz.entity.Userinfo;
import com.example.jiangz.service.UserinfoService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JiangZ on 2015-08-25.
 */
public class UserinfoServiceImpl implements UserinfoService {

    private UserDBAdapter userDBAdapter ;

    public UserinfoServiceImpl(Context context){
        userDBAdapter = new UserDBAdapter(context);
    }

    @Override
    public long createUser(Userinfo userinfo) {

        long result = 0l;

        try {
            userDBAdapter.open("write");
            result = userDBAdapter.createUser(userinfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            userDBAdapter.closeDB();
            userDBAdapter.closeHelper();
        }

        return result;
    }

    @Override
    public int deleteUsersById(Integer id) {

        int result = 0;

        try{
            userDBAdapter.open("write");
            result = userDBAdapter.deleteUsersById(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            userDBAdapter.closeDB();
            userDBAdapter.closeHelper();
        }

        return result;
    }

    @Override
    public int update(Userinfo userinfo) {
        int result = 0;

        try{
            userDBAdapter.open("write");
            result = userDBAdapter.update(userinfo);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            userDBAdapter.closeDB();
            userDBAdapter.closeHelper();
        }

        return result;
    }

    @Override
    public List<Userinfo> fetchAllUsers() {
        List<Userinfo> userinfos = null;
        try{
            userDBAdapter.open("read");
            userinfos = userDBAdapter.fetchAllUsers();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            userDBAdapter.closeDB();
            userDBAdapter.closeHelper();
        }
        return userinfos;
    }

    @Override
    public List<Userinfo> fetchUsersByName(String username) {
        List<Userinfo> userinfos = null;
        try{
            userDBAdapter.open("read");
            userinfos = userDBAdapter.fetchUsersByName(username);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            userDBAdapter.closeDB();
            userDBAdapter.closeHelper();
        }
        return userinfos;
    }

    @Override
    public Userinfo validateUser(String username, String password) {
        Userinfo userinfo = null;
        try {
            userDBAdapter.open("read");
            userinfo = userDBAdapter.validateUser(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            userDBAdapter.closeDB();
            userDBAdapter.closeHelper();
        }

        return userinfo;
    }
}
