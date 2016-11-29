package com.example.izac.lr3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.izac.myapplication.backend.productApi.model.Product;


public class ProdDetail extends Activity implements android.view.View.OnClickListener {

    Button btnSave , btnClose;
    EditText Name;
    EditText Price;


    private long _Product_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnClose = (Button) findViewById(R.id.btnClose);

        Name = (EditText) findViewById(R.id.editTextName);
        Price = (EditText) findViewById(R.id.editPrice);

        btnSave.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        Intent intent = getIntent();
        _Product_Id = intent.getLongExtra("prod_Id", (long)0);
        Long[] params = {_Product_Id};

        Price.setText("");

        if(_Product_Id!= (long) 0) {
            new getProductByIdAsyncTask() {
                @Override
                protected void onPostExecute(Product product) {
                    if (product.getName() != null && product.getPrice() != null) {
                        Price.setText(product.getPrice());
                        Name.setText(product.getName());
                    }
                }
            }.execute(params);
        }
        }

    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)) {

            String[] param = {Price.getText().toString(), Name.getText().toString(),String.valueOf(_Product_Id)};

            if (_Product_Id == 0) {
                //_Product_Id = repo.insert(product);
                new AddProductAsyncTask(){
                    @Override
                    protected void onPostExecute(Product product) {
                        Toast.makeText(ProdDetail.this, "New Product Insert", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }.execute(param);

            } else {
                new EditProductAsyncTask(){
                    @Override
                    protected void onPostExecute(Product product) {
                        Toast.makeText(ProdDetail.this, "Product Record updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }.execute(param);
            }
        } else if (view == findViewById(R.id.btnClose)) {
            finish();
        }


    }
}

