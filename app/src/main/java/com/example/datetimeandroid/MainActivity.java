package com.example.datetimeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtDatetime;
    Button btnTime;
    Button btnDate;
    Calendar datetime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtDatetime = findViewById(R.id.txtDateTime);
        btnTime = findViewById(R.id.btnDate);
        btnDate = findViewById(R.id.btnTime);


        TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                datetime.set(Calendar.MINUTE, minute);
                txtDatetime.setText(DateUtils.formatDateTime(getApplicationContext(),
                        datetime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                                | DateUtils.FORMAT_SHOW_TIME));
            }
        };

        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                datetime.set(Calendar.YEAR,year);
                datetime.set(Calendar.MONTH, month);
                datetime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                txtDatetime.setText(DateUtils.formatDateTime(getApplicationContext(),
                        datetime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                                | DateUtils.FORMAT_SHOW_TIME));
            }
        };

        txtDatetime.setText(DateUtils.formatDateTime(this,
                datetime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
        btnDate.setOnClickListener(view -> new DatePickerDialog(MainActivity.this, d,
                datetime.get(Calendar.YEAR),
                datetime.get(Calendar.MONTH),
                datetime.get(Calendar.DAY_OF_MONTH))
                .show());
        btnTime.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view){
                new TimePickerDialog(MainActivity.this, t,
                    datetime.get(Calendar.HOUR_OF_DAY),
                    datetime.get(Calendar.MINUTE), true)
                    .show();
            }
        });


    }
}