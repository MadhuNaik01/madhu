package com.example.program_22;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button search;
    EditText txtdatepicker;
    TextView txtdisplay;
    DatePickerDialog picker;
    String URL = "content://com.example.vtucontentprovider/agenda";
    Uri var = Uri.parse(URL);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtdisplay = findViewById(R.id.txt_disp);
        txtdatepicker = findViewById(R.id.calendarView);

        txtdatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        txtdatepicker.setText(date+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                picker.show();
            }
        });

        search = findViewById(R.id.button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] mProjection = {"agenda_date" , "agenda_time", "agenda_content"};
                String searchdate = txtdatepicker.getText().toString().trim();

                String where="agenda_date=?";

                Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.vtucontentprovider/agenda"), mProjection, where, new String[]{searchdate},null);

                txtdisplay.setText(" ");
                String res  = " ";

                if(cursor.getCount() == 0)
                {
                    Toast.makeText(getBaseContext(),"No Data Available", Toast.LENGTH_LONG).show();
                }

                while(cursor != null && cursor.moveToNext())
                {
                    String adate = cursor.getString(cursor.getColumnIndex("agenda_date"));
                    String atime = cursor.getString(cursor.getColumnIndex("agenda_time"));
                    String acontent = cursor.getString(cursor.getColumnIndex("agenda_content"));

                    res = res + "\n" + adate +"     "+ "   " + atime + "    " + acontent ;
                    Toast.makeText(getBaseContext(), res, Toast.LENGTH_LONG).show();
                }
                txtdisplay.setText(res);
            }
        });
    }
}
