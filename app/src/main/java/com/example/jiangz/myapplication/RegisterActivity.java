package com.example.jiangz.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiangz.entity.Userinfo;
import com.example.jiangz.service.impl.UserinfoServiceImpl;

/**
 * Created by JiangZ on 2015-08-25.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private UserinfoServiceImpl userinfoService;

    private EditText username;

    private  EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {

        username = (EditText)findViewById(R.id.register_username);
        password = (EditText)findViewById(R.id.register_password);

        userinfoService = new UserinfoServiceImpl(this);

        switch (v.getId()){
            case R.id.save :

                Userinfo userinfo = new Userinfo();
                userinfo.setUsername(username.getText().toString());
                userinfo.setPassword(password.getText().toString());
                userinfo.setIsEnable(1);
                userinfo.setIsLock(1);

                long result = userinfoService.createUser(userinfo);

                if(result > 0l){
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                }

                break;
        }

    }
}
