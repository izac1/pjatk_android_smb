package com.example.izac.lr1_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    Button listbtn;

    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mRelativeLayout;
    private Button mButton;
    private TextView mTextView;

    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listbtn = (Button) findViewById(R.id.button);


        //Listner first button
        View.OnClickListener Onlistbtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        };
        //Set listner
        listbtn.setOnClickListener(Onlistbtn);

    }

    protected void onResume() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String backgroundColor = mSharedPreferences.getString("bg", "#FFFFFF");
        String textColor = mSharedPreferences.getString("textclr", "#000000");
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        mRelativeLayout.setBackgroundColor(Color.parseColor(backgroundColor));
        listbtn.setTextColor(Color.parseColor(textColor));


        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mi = menu.add(0, 1, 0, "Preferences");
        mi.setIntent(new Intent(this, PrefActivity.class));
        return super.onCreateOptionsMenu(menu);
    }

}
