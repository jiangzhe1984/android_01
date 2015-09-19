package com.example.jiangz.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jiangz.adapters.UserinfoListAdapter;
import com.example.jiangz.entity.Userinfo;
import com.example.jiangz.service.impl.UserinfoServiceImpl;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by JiangZ on 2015-08-29.
 */
@EActivity(R.layout.activity_userlist)
public class UserListViewActivity extends Activity{

    @ViewById(R.id.user_listView)
     ListView user_listView;

    @Bean
     UserinfoServiceImpl userinfoService;

    @AfterViews
    public void init(){
        List<Userinfo> userinfos = userinfoService.fetchAllUsers();
        String flags[] = {"username"};
        int itemIDs[] = {R.id.view_username};
       UserinfoListAdapter userinfoListAdapter = new UserinfoListAdapter(this,userinfos,R.layout.layout_userinfo,flags,itemIDs);
        user_listView.setAdapter(userinfoListAdapter);

    }

    @ItemClick(R.id.user_listView)
    public void listItemClick(int position){
        Userinfo userinfo =  (Userinfo)user_listView.getItemAtPosition(position);
        Toast.makeText(UserListViewActivity.this, userinfo.getId()+"||"+userinfo.getUsername(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



      /*  user_listView.setAdapter(new ArrayAdapter<Userinfo>(this,
                android.R.layout.simple_list_item_1, userinfos));

        user_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Userinfo userinfo =  (Userinfo)user_listView.getItemAtPosition(position);
                //Log.w("UserListViewActivity",String.valueOf(position));
                Toast.makeText(UserListViewActivity.this, userinfo.getId()+"||"+userinfo.getUsername(), Toast.LENGTH_LONG).show();
            }
        });*/

    }
}
