package com.qianfeng.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private String name;
    private String pw;

    public static SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();

        username = ((EditText) findViewById(R.id.username));
        password = ((EditText) findViewById(R.id.password));
        //username焦点监听
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                out:
                if (!b) {
                    name = username.getText().toString();
                    Cursor cursor = db.rawQuery("select *from  " + DBHelper.userTable, null);
                    if (cursor == null) {
                        Toast.makeText(Login.this, "该用户不存在，请先注册！", Toast.LENGTH_SHORT).show();
                    } else {
                        while (cursor.moveToNext()) {
                            String name1 = cursor.getString(cursor.getColumnIndex("username"));
                            if (name.equals(name1)) {
                                break out;
                            }
                        }
                        Toast.makeText(Login.this, "该用户不存在，请先注册！", Toast.LENGTH_SHORT).show();
                        username.setText("");
                    }
                }
            }
        });
    }

    public void login(View view) {
        name = username.getText().toString();
        pw = password.getText().toString();
        if (name != null && pw != null) {
            Cursor cursor = db.rawQuery("select *from  " + DBHelper.userTable, null);
            if (cursor == null) {
                Toast.makeText(Login.this, "该用户不存在，请先注册！", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    String name1 = cursor.getString(cursor.getColumnIndex("username"));
                    if (name.equals(name1)) {
                        String pw1 = cursor.getString(cursor.getColumnIndex("password"));
                        if (pw.equals(pw1)) {
                            Intent intent = new Intent(this, MainActivity.class);
                            User user = new User(name, pw);
                            intent.putExtra("user", user);
                            startActivity(intent);
                            break;
                        } else {
                            Toast.makeText(Login.this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                            password.setText("");
                        }

                    }
                }
            }
        } else {
            Toast.makeText(Login.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
