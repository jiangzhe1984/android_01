package com.example.jiangz.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.jiangz.entity.Userinfo;
import com.example.jiangz.service.impl.UserinfoServiceImpl;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @Bean
    UserinfoServiceImpl userinfoService;

    @ViewById(R.id.username)
     EditText username;

    @ViewById(R.id.password)
     EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


  /*  @AfterViews
    void init(){
        username.setText("bb");
        password.setText("bb");
   }*/


    @Click
    void submit(){
        Userinfo userinfo = userinfoService.validateUser(username.getText().toString(),password.getText().toString());
        if(userinfo == null){
            Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
        }else{
            if(!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(password.getText())){
                username.setText("");
                password.setText("");
                username.setCursorVisible(false);
                password.setCursorVisible(false);
            }
            Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,AccountListViewActivity_.class);
            startActivity(intent);
        }
    }

    @Click
    void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
