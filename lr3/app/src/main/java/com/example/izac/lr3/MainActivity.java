package com.example.izac.lr3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.izac.myapplication.backend.productApi.ProductApi;




public class MainActivity extends AppCompatActivity {


    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.button);

        View.OnClickListener Onlistbtn = new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProdListActivity.class);
                startActivity(intent);
            }
        };


        btnAdd.setOnClickListener(Onlistbtn);
    }


}

   /*@Override
            public void onClick(View view) {
                String[] a = {"colla","10.7"};
                new AddProductAsyncTask().execute(a);
            }*/