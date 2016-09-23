package com.qianfeng.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LookNote extends AppCompatActivity {

    private ListView lv;
    private List<Note> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_note);
        lv = ((ListView) findViewById(R.id.lv));
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        Cursor cursor = Login.db.rawQuery("select *from " + DBHelper.noteTable + " where username=? order by time ASC", new String[]{name});
        if(cursor.getCount()==0){
            Toast.makeText(LookNote.this, "暂时没有添加数据，请返回添加！", Toast.LENGTH_SHORT).show();
        } else {
            list=new ArrayList<>();
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndex("username"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                Note note = new Note(username,time,title,content);
                list.add(note);

            }
        MyAdapter myAdapter = new MyAdapter(this, list);
        lv.setAdapter(myAdapter);
        }
    }
}
