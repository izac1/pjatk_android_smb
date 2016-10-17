package com.example.izac.lr1_1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by izac on 15.10.16.
 */

public class ProductDetail extends Activity implements android.view.View.OnClickListener{

    Button btnSave , btnClose;
    EditText Name;
    EditText Price;
    private RelativeLayout mRelativeLayout;

    private int _Product_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnClose = (Button) findViewById(R.id.btnClose);

        Name = (EditText) findViewById(R.id.editTextName);
        Price = (EditText) findViewById(R.id.editPrice);

        btnSave.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _Product_Id =0;
        Intent intent = getIntent();
        _Product_Id =intent.getIntExtra("prod_Id", 0);
        ProducRepo repo = new ProducRepo(this);
        Product product = new Product();
        product = repo.getProductById(_Product_Id);
        if(product.price!=null)
            Price.setText(String.valueOf(product.price));
        else
            Price.setText("");
        Name.setText(product.name);
    }


    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            ProducRepo repo = new ProducRepo(this);
            Product product = new Product();
            product.price= Double.parseDouble(Price.getText().toString());
            product.name=Name.getText().toString();
            product.product_ID=_Product_Id;

            if (_Product_Id==0){
                _Product_Id = repo.insert(product);

                Toast.makeText(this,"New Product Insert",Toast.LENGTH_SHORT).show();
                finish();
            }else{

                repo.update(product);
                Toast.makeText(this,"Product Record updated",Toast.LENGTH_SHORT).show();
                finish();
            }
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }


    }

    protected void onResume() {
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String backgroundColor = mSharedPreferences.getString("bg", "#FFFFFF");
        String textColor = mSharedPreferences.getString("textclr", "#000000");
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main2);
        mRelativeLayout.setBackgroundColor(Color.parseColor(backgroundColor));
        Name.setTextColor(Color.parseColor(textColor));
        Price.setTextColor(Color.parseColor(textColor));
        btnSave.setTextColor(Color.parseColor(textColor));
        btnClose.setTextColor(Color.parseColor(textColor));

        super.onResume();
    }

}
