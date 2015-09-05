package com.example.jiangz.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiangz.entity.Userinfo;
import com.example.jiangz.service.impl.UserinfoServiceImpl;

import org.w3c.dom.Text;

/**
 * Created by JiangZ on 2015-08-25.
 */
public class RegisterActivity extends Activity implements View.OnClickListener{

    private UserinfoServiceImpl userinfoService;

    private EditText username;

    private  EditText password;

    private Button register_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_save = (Button)findViewById(R.id.zc_save);

        register_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        username = (EditText)findViewById(R.id.zc_username);
        password = (EditText)findViewById(R.id.zc_password);

        userinfoService = new UserinfoServiceImpl(this);

        switch (v.getId()){
            case R.id.zc_save :

                Userinfo userinfo = new Userinfo();
                userinfo.setUsername(username.getText().toString());
                userinfo.setPassword(password.getText().toString());
                userinfo.setIsEnable(1);
                userinfo.setIsLock(1);

                long result = userinfoService.createUser(userinfo);

                if(result > 0l){
                    if(!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(password.getText())){
                        username.setText("");
                        password.setText("");
                    }
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, UserListViewActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }

}
