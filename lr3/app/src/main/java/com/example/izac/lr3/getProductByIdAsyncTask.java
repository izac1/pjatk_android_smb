package com.example.izac.lr3;

import android.os.AsyncTask;

import com.example.izac.myapplication.backend.productApi.ProductApi;
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


public class getProductByIdAsyncTask extends AsyncTask<Long,Long,Product> {

        private ProductApi myApiService=null;


        @Override
        protected Product doInBackground(Long... params) {
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
                return myApiService.get((long)params[0]).execute();
                        //execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

