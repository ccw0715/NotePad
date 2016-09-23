package com.qianfeng.notepad;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText password2;

    private String name;
    private String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = ((EditText) findViewById(R.id.username));
        password = ((EditText) findViewById(R.id.password));
        password2 = ((EditText) findViewById(R.id.password2));
        //监听事件焦点
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    name = username.getText().toString();
                    if(name.equals("")){
                        Toast.makeText(Register.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    }
                    //查找数据库
                    Cursor cursor = Login.db.rawQuery("select *from  " + DBHelper.userTable, null);
                    if (cursor == null) {

                    } else {
                        //遍历数据库
                        while (cursor.moveToNext()) {
                            String name1 = cursor.getString(cursor.getColumnIndex("username"));
                            if (name.equals(name1)) {
                                Toast.makeText(Register.this, "该用户已经被注册！", Toast.LENGTH_SHORT).show();
                                username.setText("");
                            }
                        }
                    }
                }
            }
        });
    }

    public void ensure(View view) {
        name = username.getText().toString();
        pw = password.getText().toString();
        String pw2 = password2.getText().toString();
        if (name.equals("") | pw .equals("") | pw2.equals("") ) {
            Toast.makeText(Register.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            if (pw.equals(pw2)) {
                ContentValues values = new ContentValues();
                values.put("username", name);
                values.put("password", pw);
                long insert = Login.db.insert(DBHelper.userTable, null, values);

                Intent intent = new Intent(this, MainActivity.class);
                User user = new User(name, pw);
                intent.putExtra("user", user);
                startActivity(intent);
            } else {
                Toast.makeText(Register.this, "请重新输入确认密码！", Toast.LENGTH_SHORT).show();
                password2.setText("");
            }
        }

    }

    public void clear(View view) {
        username.setText("");
        password.setText("");
        password2.setText("");
    }
}
