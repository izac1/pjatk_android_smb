package com.example.izac.lr4;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.izac.lr4.db.Place;
import com.example.izac.lr4.db.PlaceRepo;


public class ListActivity extends AppCompatActivity implements LocationListener {

    Button listbtn,mapbtn;
    EditText name;
    EditText desc;
    Double lt;
    Double lg;

    protected LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listbtn = (Button) findViewById(R.id.button2);
        mapbtn = (Button) findViewById(R.id.button3);

        name = (EditText) findViewById(R.id.editText);
        desc = (EditText) findViewById(R.id.editText2);

        locationManager = (LocationManager) getSystemService(ListActivity.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(ListActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ListActivity.this);

        //Listner first button
        View.OnClickListener Onlistbtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaceRepo repo = new PlaceRepo(ListActivity.this);
                Place place = new Place();
                place.name = name.getText().toString();
                place.desc = desc.getText().toString();
                place.x = lg;
                place.y = lt;

                repo.insert(place);
                Toast.makeText(ListActivity.this,"New Place Insert",Toast.LENGTH_SHORT).show();

            }
        };

        View.OnClickListener mapbtnn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        };

        //Set listner
        mapbtn.setOnClickListener(mapbtnn);
        listbtn.setOnClickListener(Onlistbtn);


    }

    @Override
    public void onLocationChanged(Location location) {
        lt = location.getLatitude();
        lg = location.getLongitude();
        System.out.println(location.getLatitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    protected void onResume() {
        name.setText("Set Name");
        desc.setText("Set desc");
        super.onResume();
    }

}
