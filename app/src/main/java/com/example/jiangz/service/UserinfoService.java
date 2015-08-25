package com.example.jiangz.service;

import com.example.jiangz.entity.Userinfo;

import java.util.List;

/**
 * Created by JiangZ on 2015-08-25.
 */
public interface UserinfoService {

    long createUser(Userinfo userinfo);

    int deleteUsersById(Integer id);

    int update(Userinfo userinfo);

    List<Userinfo> fetchAllUsers();

    List<Userinfo> fetchUsersByName(String username);

    Userinfo validateUser(String username,String password);

}
