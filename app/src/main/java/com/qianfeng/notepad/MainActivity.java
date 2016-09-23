package com.qianfeng.notepad;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private User user;
    public static String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(name==null){
            Intent intent = getIntent();
            user = (User) intent.getSerializableExtra("user");
            name = user.getUsername();
        }else{

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        toolbar.setLogo(R.mipmap.ic_launcher);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        name=null;
    }

    public void addNote(View view) {
        Intent intent = new Intent(this, NoteBook.class);
        startActivity(intent);
    }

    public void look(View view) {
        Intent intent = new Intent(this, LookNote.class);
        intent.putExtra("username",name);
        startActivity(intent);
    }
}