package com.example.jiangz.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jiangz.entity.Userinfo;
import com.example.jiangz.service.impl.UserinfoServiceImpl;

import java.util.List;

/**
 * Created by JiangZ on 2015-08-29.
 */
public class UserListViewActivity extends Activity{

    private ListView user_listView;

    private UserinfoServiceImpl userinfoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        userinfoService = new UserinfoServiceImpl(this);

        List<Userinfo> userinfos = userinfoService.fetchAllUsers();

        user_listView = (ListView)findViewById(R.id.user_listView);

        user_listView.setAdapter(new ArrayAdapter<Userinfo>(this,
                android.R.layout.simple_list_item_1, userinfos));

        user_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Userinfo userinfo =  (Userinfo)user_listView.getItemAtPosition(position);
                //Log.w("UserListViewActivity",String.valueOf(position));
                Toast.makeText(UserListViewActivity.this, userinfo.getId()+"||"+userinfo.getUsername(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
