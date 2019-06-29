package com.nguyenminh.neocloud.presentasion.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;

import com.nguyenminh.neocloud.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkReportActivity extends AppCompatActivity {
    @BindView(R.id.tv_date) TextView tv_date;
    private Calendar myCalendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_report);

        ButterKnife.bind(this);
        myCalendar = Calendar.getInstance();
        updateDate();
    }


    @OnClick(R.id.tv_date)
    public void setDate(){

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

        };
        new DatePickerDialog(WorkReportActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.iv_rewind)
    public void dateRewind(){
        myCalendar.add(Calendar.DATE, -1);
        updateDate();
    }

    @OnClick(R.id.iv_forward)
    public void dateForword(){
        myCalendar.add(Calendar.DATE, 1);
        updateDate();
    }
    private void updateDate() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tv_date.setText(sdf.format(myCalendar.getTime()));
    }
}
