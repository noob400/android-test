package com.example.materialtest;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private int position=0;

    private TextView name;

    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();
        Button login=(Button)findViewById(R.id.login);
        Button forget=(Button)findViewById(R.id.forget);
        Button register=(Button)findViewById(R.id.register);
        login.setOnClickListener(this);
        forget.setOnClickListener(this);
        register.setOnClickListener(this);
        name=(TextView)findViewById(R.id.account);
        password=(TextView)findViewById(R.id.password);
        final CheckBox student=(CheckBox)findViewById(R.id.student);
        final CheckBox teacher=(CheckBox)findViewById(R.id.teacher);
        student.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    position=1;
                    teacher.setChecked(false);
                }
            }
        });
        teacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    position=2;
                    student.setChecked(false);
                }
            }
        });
        List<User> userexit = DataSupport.findAll(User.class);
        List<Teacher> teacherexit = DataSupport.findAll(Teacher.class);
        if(userexit.size()==0&&teacherexit.size()==0){
            Toast.makeText(this,"请先注册！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                if(position==1) {
                    String name1 = name.getText().toString();
                    String password1 = password.getText().toString();
                    if (!name1.equals("") && !password1.equals("")) {
                        List<User> users = DataSupport.findAll(User.class);
                        for (User user : users) {
                            if ((user.getName()).equals(name.getText().toString())) {
                                if ((user.getName()).equals(name.getText().toString()) && (user.getPassword()).equals(password.getText().toString())) {
                                    Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    } else {
                        Toast.makeText(this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                    }
                }else if(position==2){

                    if (!name.getText().toString().equals("") && !name.getText().toString().equals("")) {
                        List<Teacher> teachers = DataSupport.findAll(Teacher.class);
                        for (Teacher teacher : teachers) {
                            if ((teacher.getName()).equals(name.getText().toString())) {
                                if ((teacher.getName()).equals(name.getText().toString()) && (teacher.getPassword()).equals(password.getText().toString())) {
                                    Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    } else {
                        Toast.makeText(this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"请选择是学生还是老师！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget:
                Intent intent2=new Intent(MainActivity.this,ForgetActivity.class);
                startActivity(intent2);
                break;
            case R.id.register:
                Intent intent= new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }


}
