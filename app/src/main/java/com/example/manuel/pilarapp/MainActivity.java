package com.example.manuel.pilarapp;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    static final LatLng MELBOURNE = new LatLng(-37.813, 144.962);
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);

        Drawer result =  new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withToolbar(toolbar)
                .addDrawerItems(
                        //pass your items here
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Log.d("TAG","PULSADO");
                        return false;
                    }
                })
               .build();
    }

    @Override
    public void onMapReady(GoogleMap map) {

        double longitude = -1;
        double latitude = -1;

        Geocoder coder = new Geocoder(this);
        try {
            ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName("Avda. Puerta Sancho, 30, Zaragoza", 50);
            for(Address add : adresses){
                Log.d("COUNTRY",add.getCountryName());
                if (add.getCountryName().equals("Spain")) {//Controls to ensure it is right address such as country etc.
                    longitude = add.getLongitude();
                    latitude = add.getLatitude();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LatLng coord = new LatLng(latitude, longitude);
        Marker melbourne = map.addMarker(new MarkerOptions()
                .position(coord)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
