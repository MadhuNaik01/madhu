package com.example.program_21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button addagenda;
    EditText date, time, meetingagenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addagenda = findViewById(R.id.button);

        date = findViewById(R.id.editText);
        time = findViewById(R.id.editText1);
        meetingagenda = findViewById(R.id.editText2);

        addagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addage = meetingagenda.getText().toString();
                String adddate = date.getText().toString();
                String addtime = time.getText().toString();

                Toast.makeText(getBaseContext(),addage, Toast.LENGTH_SHORT).show();

                ContentValues values = new ContentValues();
                values.put("agenda_date",adddate);
                values.put("agenda_time",addtime);
                values.put("agenda_content",addage);

                Uri id = getContentResolver().insert(Uri.parse("content://com.example.vtucontentprovider/agenda"),values);
                Toast.makeText(getBaseContext(),id.toString(), Toast.LENGTH_LONG).show();

                Toast.makeText(getBaseContext(),"Data Inserted Successfully", Toast.LENGTH_LONG).show();

            }
        });
    }
}
