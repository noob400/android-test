package com.example.materialtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private int position=0;
    private EditText registername;
    private EditText registerpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button toregister=(Button)findViewById(R.id.toregister);
        toregister.setOnClickListener(this);
        registername=(EditText) findViewById(R.id.registeraccount);
        registerpassword=(EditText) findViewById(R.id.registerpassword);
        final CheckBox student=(CheckBox)findViewById(R.id.student1);
        final CheckBox teacher=(CheckBox)findViewById(R.id.teacher1);
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
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.toregister:
                if(position==1) {
                    List<User> users=DataSupport.where("name=?",registername.getText().toString()).find(User.class);
                            if (users.size()==0) {
                                User user = new User();
                                user.setName(registername.getText().toString());
                                user.setPassword(registerpassword.getText().toString());
                                user.save();
                                Intent intent = new Intent(RegisterActivity.this, ContentActivity.class);
                                startActivity(intent);
                                Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "用户名存在！", Toast.LENGTH_SHORT).show();
                            }

                }else if(position==2){
                    List<Teacher> users = DataSupport.where("name=?",registername.getText().toString()).find(Teacher.class);
                            if (users.size()==0) {
                                Teacher user = new Teacher();
                                user.setName(registername.getText().toString());
                                user.setPassword(registerpassword.getText().toString());
                                user.save();
                                Intent intent = new Intent(RegisterActivity.this, ContentActivity.class);
                                startActivity(intent);
                                Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "用户名存在！", Toast.LENGTH_SHORT).show();
                            }

                }else {
                    Toast.makeText(this,"请选择是学生还是老师！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
