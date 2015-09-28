package com.example.manuel.pilarapp;


import android.graphics.Color;
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

import com.example.manuel.pilarapp.Objects.Actos;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import com.example.manuel.pilarapp.Objects.Request;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    static final LatLng MELBOURNE = new LatLng(-0.8901245089344004, 41.65140964533784);
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);

    }

    public LatLng getLangLong(String address)
    {
        try {
            Geocoder coder = new Geocoder(this);
            ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName("Avda. Puerta Sancho, 30, Zaragoza", 50);
            for(Address add : adresses){
                Log.d("COUNTRY",add.getCountryName());
                if (add.getCountryName().equals("Spain")) {//Controls to ensure it is right address such as country etc.
                    double longitude = add.getLongitude();
                    double latitude = add.getLatitude();
                    return new LatLng(latitude,longitude);
                }
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return  null;
    }

    @Override
    public void onMapReady(GoogleMap map) {

        double longitude = -1;
        double latitude = -1;

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Actos").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withName("Algo").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));

        ApiManager am = new ApiManager();
        am.getApiService().getRequest(new Callback<Request>() {
            @Override
            public void success(Request resultado, Response response) {
                List<Actos> actos = resultado.getResult();
                for (Actos a :actos){
                    Log.d("DATA", a.getDescription());
                    Log.d("DATA", a.getPrograma());
                    Log.d("DATA", a.getEndDate());
                    Log.d("DATA", a.getStartDate());
                    Log.d("DATA", a.getTitle());
                    Log.d("DATA", a.getEntidad().getTitle());
                    Log.d("DATA", a.getGeometry().getCoordinates().toString());
                    Log.d("DATA", a.getDiasParaTerminar()+" ");
                    Log.d("DATA", a.getLugarInscripcion().getRequisitosInscrip());

                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG", "Error: " + error.getMessage() + error.getStackTrace());
            }
        });


        final Marker melbourne = map.addMarker(new MarkerOptions()
                .position(new LatLng(1,1))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //Place the Map View nearby Zaragoza
        map.moveCamera(CameraUpdateFactory.newLatLngZoom((  new LatLngBounds(
                                                            new LatLng(41.62827478,-0.97400665),
                                                            new LatLng(41.6698344,-0.83255768))).getCenter()
                                                            ,12));
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withToolbar(toolbar)
                .addDrawerItems(item1, item2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        return false;
                    }
                })
                .build();
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
