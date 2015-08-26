package com.example.jiangz.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.jiangz.entity.Userinfo;
import com.example.jiangz.service.impl.UserinfoServiceImpl;

public class MainActivity extends Activity implements View.OnClickListener{

    private UserinfoServiceImpl userinfoService;

    private EditText username;

    private  EditText password;

    private Button submit;

    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = (Button) findViewById(R.id.submit);
        register = (Button) findViewById(R.id.register);
        submit.setOnClickListener(this);
        register.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
         username = (EditText)findViewById(R.id.username);
         password = (EditText)findViewById(R.id.password);
        //  Toast.makeText(MainActivity.this, "用户名："+username.getText().toString()+"||密码："+password.getText().toString(), Toast.LENGTH_LONG).show();
        userinfoService = new UserinfoServiceImpl(this);

    /*    if(v.getId() == R.id.submit){
            Userinfo userinfo = userinfoService.validateUser(username.getText().toString(),password.getText().toString());
            if(userinfo == null){
                Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
            }

        }else if(v.getId() == R.id.register){
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }*/

      switch (v.getId()){
          case R.id.submit:
              Userinfo userinfo = userinfoService.validateUser(username.getText().toString(),password.getText().toString());
              if(userinfo == null){
                  Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
              }else{
                  Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
              }
              break;
          case R.id.register:
              Intent intent = new Intent(this, RegisterActivity.class);
              startActivity(intent);
              break;

          default: break;
      }

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
