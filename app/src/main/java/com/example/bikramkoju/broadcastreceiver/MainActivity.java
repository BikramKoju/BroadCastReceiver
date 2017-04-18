package com.example.bikramkoju.broadcastreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnSetTime;
    TextView tvSetTime;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSetTime=(TextView) findViewById(R.id.textview);
        btnSetTime=(Button)findViewById(R.id.settime);
        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                timePickerDialog=new TimePickerDialog(MainActivity.this,onSetListener,
                        calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
                timePickerDialog.show();
            }

            TimePickerDialog.OnTimeSetListener onSetListener=new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Calendar cal=Calendar.getInstance();
                    Calendar calnow= (Calendar) cal.clone(); //cal vanni static thiyo so make clone ie calnow
                    calnow.set(Calendar.HOUR_OF_DAY,hourOfDay);
                    calnow.set(Calendar.MINUTE,minute);
                    calnow.set(Calendar.SECOND,0);
                    alarmset(calnow);
                }
            };
        });
    }

    private void alarmset(Calendar calnow) {
        tvSetTime.setText("Alarm set at:"+calnow.getTime().getHours()+":"+calnow.getTime().getMinutes());
        Intent i = new Intent(MainActivity.this,BroadcastClass.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,12,i,0);
        AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calnow.getTimeInMillis(),pendingIntent);


    }
}
