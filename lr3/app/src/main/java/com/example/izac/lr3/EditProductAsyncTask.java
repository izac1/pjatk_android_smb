package com.example.izac.lr3;

import android.os.AsyncTask;

import com.example.izac.myapplication.backend.productApi.ProductApi;
import com.example.izac.myapplication.backend.productApi.model.Product;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by izac on 28.11.16.
 */


    public class EditProductAsyncTask extends AsyncTask<String,Void,Product> {

        private ProductApi myApiService=null;


        @Override
        protected Product doInBackground(String... params) {
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

            Product p = new Product();
            p.setName(params[1]);
            p.setPrice(params[0]);

            try {
                return myApiService.update(Long.parseLong(params[2]),p).execute();
                //.add(params[0], false).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

