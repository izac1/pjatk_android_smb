package com.example.izac.lr1_1;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;

public class Main3Activity extends ListActivity implements View.OnClickListener {

    Button btnAdd;
    TextView prod_id;
    private TextView name, price;
    private SharedPreferences mSharedPreferences;
    private Integer _Prod_id;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ProductDetail.class);
        intent.putExtra("prod_Id", 0);
        startActivity(intent);
    }

    protected void onResume() {
        ProducRepo repo = new ProducRepo(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ArrayList<HashMap<String, String>> productList = repo.getProductList();
        if (productList.size() != 0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    prod_id = (TextView) view.findViewById(R.id.prod_Id);
                    String prodId = prod_id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(), ProductDetail.class);
                    objIndent.putExtra("prod_Id", Integer.parseInt(prodId));
                    startActivity(objIndent);
                }
            });

            ListAdapter adapter = new SimpleAdapter(Main3Activity.this, productList, R.layout.view_prod_entry, new String[]{"id", "name", "price"}, new int[]{R.id.prod_Id, R.id.prod_name, R.id.prod_price}) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    String textColor = mSharedPreferences.getString("textclr", "#000000");
                    name = (TextView) view.findViewById(R.id.prod_name);
                    price = (TextView) view.findViewById(R.id.prod_price);
                    name.setTextColor(Color.parseColor(textColor));
                    price.setTextColor(Color.parseColor(textColor));

                    return view;
                }
            };
            setListAdapter(adapter);
            registerForContextMenu(lv);
        } else {
            Toast.makeText(this, "No Products!", Toast.LENGTH_SHORT).show();
        }

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String backgroundColor = mSharedPreferences.getString("bg", "#FFFFFF");
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main3);
        mRelativeLayout.setBackgroundColor(Color.parseColor(backgroundColor));
        String textColor = mSharedPreferences.getString("textclr", "#000000");
        btnAdd.setTextColor(Color.parseColor(textColor));

        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId()== android.R.id.list) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.delete:
                TextView a = (TextView) info.targetView.findViewById(R.id.prod_Id);
                String id = a.getText().toString();
                ProducRepo repo = new ProducRepo(this);
                repo.delete(Integer.parseInt(id));
                Toast.makeText(this, "Product Record Deleted", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
