package com.qianfeng.notepad;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NoteBook extends AppCompatActivity {

    private User user;
    private Spinner year;
    private Spinner month;
    private Spinner day;

    private List<Integer> yearList;
    private List<Integer> monthList;
    private List<Integer> dayList;
    private int[] dayData = new int[]{0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private EditText title;
    private EditText content;
    private String time;
    private String name=MainActivity.name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book);
        title = ((EditText) findViewById(R.id.title));
        content = ((EditText) findViewById(R.id.content));
        year = ((Spinner) findViewById(R.id.year));
        month = ((Spinner) findViewById(R.id.month));
        day = ((Spinner) findViewById(R.id.day));
        initYearData();
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, yearList);
        year.setAdapter(adapter1);
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentYear = yearList.get(i);
                if (currentYear % 400 == 0 || (currentYear % 4 == 0 && currentYear % 100 != 0)) {
                    dayData[2] = 29;
                }
                initMonthData();
                ArrayAdapter adapter2 = new ArrayAdapter(NoteBook.this, android.R.layout.simple_list_item_1, monthList);
                month.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentMonth = monthList.get(i);
                initDayData(currentMonth);
                ArrayAdapter adapter3 = new ArrayAdapter(NoteBook.this, android.R.layout.simple_list_item_1, dayList);
                day.setAdapter(adapter3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentDay = dayList.get(i);
                time=currentYear + "-" + currentMonth + "-" + currentDay;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initYearData() {
        yearList = new ArrayList<>();
        for (int i = 1900; i < 2017; i++) {
            yearList.add(i);
        }
    }

    private void initMonthData() {
        monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i);
        }
    }

    private void initDayData(int month) {
        dayList = new ArrayList<>();
        int count = dayData[month];
        for (int i = 1; i <= count; i++) {
            dayList.add(i);
        }
    }

    public void submit(View view) {
        String t = title.getText().toString();
        String s = content.getText().toString();
        ContentValues values = new ContentValues();
        values.put("username", name);
        values.put("time",time);
        values.put("title",t);
        values.put("content", s);
        long insert = Login.db.insert(DBHelper.noteTable, null, values);

        Note note = new Note(name, time, t, s);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
