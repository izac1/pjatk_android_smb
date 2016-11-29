package com.example.izac.lr3;

import android.os.AsyncTask;

import com.example.izac.myapplication.backend.productApi.ProductApi;
import com.example.izac.myapplication.backend.productApi.model.CollectionResponseProduct;
import com.example.izac.myapplication.backend.productApi.model.Product;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by izac on 27.11.16.
 */


public class getAllProdAsyncTask extends AsyncTask<Void,Void,List<Product>> {

        private ProductApi myApiService=null;


        @Override
        protected List<Product> doInBackground(Void... params) {
            if(myApiService == null) {
                ProductApi.Builder builder = new ProductApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://android-150722.appspot.com/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                myApiService = builder.build();
            }



            try {
                List<Product> res = myApiService.list().execute().getItems();
                 if(res!=null){
                     return res;
                 }else{
                     return new ArrayList<Product>();
                 }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new ArrayList<>();
        }

    }

