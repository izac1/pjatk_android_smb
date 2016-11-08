package com.example.izac.send_messege;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView text;
    Button send;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.editText);
        send = (Button) findViewById(R.id.button);


        View.OnClickListener Onlistbtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putExtra("com.example.izac.send_messege.broadcast.Message",text.getText().toString());
                intent.setAction("com.example.izac.send_messege.custom_action");
                sendBroadcast(intent);
            }
        };

        send.setOnClickListener(Onlistbtn);
    }
}
