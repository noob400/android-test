package com.example.materialtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class ForgetActivity extends AppCompatActivity implements View.OnClickListener{
    private int position=0;
    private EditText newcount;
    private EditText newpassword;
    private EditText newpassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Button tochange=(Button)findViewById(R.id.tochange);
        newcount =(EditText)findViewById(R.id.newcount);
        newpassword=(EditText)findViewById(R.id.newpassword);
        newpassword1=(EditText)findViewById(R.id.newpassword1);
       tochange.setOnClickListener(this);
        final CheckBox student=(CheckBox)findViewById(R.id.student2);
        final CheckBox teacher=(CheckBox)findViewById(R.id.teacher2);
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
            case R.id.tochange:
                if(position==1) {
                    if (!newpassword.getText().toString().equals(newpassword1.getText().toString())) {
                        Toast.makeText(this, "2个密码必须一样", Toast.LENGTH_SHORT).show();
                    }
                    List<User> users = DataSupport.findAll(User.class);
                    for (User user : users) {
                        if (newcount.getText().toString().equals(user.getName())) {
                            if (newcount.getText().toString().equals(user.getName())) {
                                User user1 = new User();
                                user1 = user;
                                user1.setPassword(newpassword.getText().toString());
                                user1.save();
                                Intent intent = new Intent(ForgetActivity.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "请输入正确的账号！", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }else if(position==2){
                    if (!newpassword.getText().toString().equals(newpassword1.getText().toString())) {
                        Toast.makeText(this, "2个密码必须一样", Toast.LENGTH_SHORT).show();
                    }
                    List<Teacher> users = DataSupport.findAll(Teacher.class);
                    for (Teacher user : users) {
                        if (newcount.getText().toString().equals(user.getName())) {
                            if (newcount.getText().toString().equals(user.getName())) {
                                Teacher user1 = new Teacher();
                                user1 = user;
                                user1.setPassword(newpassword.getText().toString());
                                user1.save();
                                Intent intent = new Intent(ForgetActivity.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "请输入正确的账号！", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }else {
                    Toast.makeText(this,"请选择是学生还是老师！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
