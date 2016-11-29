package com.example.izac.send_messege;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView textV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textV = (TextView) findViewById(R.id.textView);

        if (null != getIntent().getExtras().getString("com.example.izac.send_messege.broadcast.Message")){
            textV.setText(getIntent().getExtras().getString("com.example.izac.send_messege.broadcast.Message"));
        }
    }
}
