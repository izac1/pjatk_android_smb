package com.example.izac.lr3;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.izac.myapplication.backend.productApi.model.CollectionResponseProduct;
import com.example.izac.myapplication.backend.productApi.model.Product;

import java.util.List;
import java.util.Objects;

public class ProdListActivity extends ListActivity implements View.OnClickListener  {
    Button btnAdd;
    TextView prod_id;
    private Context context;

    private TextView name,price;
    private Long _Prod_id;
    private RelativeLayout mRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_list);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        context = this;

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ProdDetail.class);
        intent.putExtra("prod_Id", 0);
        startActivity(intent);
    }

    protected void onResume() {
         new getAllProdAsyncTask(){
             @Override
             protected void onPostExecute(List<Product> products) {
                if(products.size()!=0){
                    ListView lv = getListView();
                    ListAdapter adapter = new SimpleAdapter(ProdListActivity.this, products, R.layout.view_prod_entry, new String[]{"id", "name", "price"}, new int[]{R.id.prod_Id, R.id.prod_name, R.id.prod_price});
                    setListAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            prod_id = (TextView) view.findViewById(R.id.prod_Id);
                            String prodId = prod_id.getText().toString();
                            Intent objIndent = new Intent(getApplicationContext(), ProdDetail.class);
                            objIndent.putExtra("prod_Id", Long.parseLong(prodId));
                            startActivity(objIndent);
                        }
                    });





                    registerForContextMenu(lv);

                } else {
                    Toast.makeText(context, "No Products!", Toast.LENGTH_SHORT).show();
                }

             }
         }.execute();
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
                String[] params = {id};
                new ProductDeleteAsyncTask() {
                    @Override
                    protected void onPostExecute(Void p) {
                        Toast.makeText(ProdListActivity.this, "Product Record Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    }
                }.execute(params);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}
