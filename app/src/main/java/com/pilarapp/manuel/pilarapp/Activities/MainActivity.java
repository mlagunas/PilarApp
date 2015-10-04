package com.pilarapp.manuel.pilarapp.Activities;


import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pilarapp.manuel.pilarapp.ApiManager;
import com.pilarapp.manuel.pilarapp.Database.DaoActos;
import com.pilarapp.manuel.pilarapp.Objects.Acto;
import com.pilarapp.manuel.pilarapp.Objects.Request;
import com.pilarapp.manuel.pilarapp.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private Location mLocation;

    private List<Acto> mActos;
    private List<Acto> mFiltered;
    private int currentDistanceFilter = -1;
    private int currentDayFilter = -1;

    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDayFilter = getIntent().getIntExtra("day", -1);

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get the location manager
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        String provider = mLocationManager.getBestProvider(criteria, false);
        Location location = mLocationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.day_9:
                currentDayFilter = 9;
                applyFilters();
                return true;
            case R.id.day_10:
                currentDayFilter = 10;
                applyFilters();
                return true;
            case R.id.day_11:
                currentDayFilter = 11;
                applyFilters();
                return true;
            case R.id.day_12:
                currentDayFilter = 12;
                applyFilters();
                return true;
            case R.id.day_13:
                currentDayFilter = 13;
                applyFilters();
                return true;
            case R.id.day_14:
                currentDayFilter = 14;
                applyFilters();
                return true;
            case R.id.day_15:
                currentDayFilter = 15;
                applyFilters();
                return true;
            case R.id.day_16:
                currentDayFilter = 16;
                applyFilters();
                return true;
            case R.id.day_17:
                currentDayFilter = 17;
                applyFilters();
                return true;
            case R.id.day_18:
                currentDayFilter = 18;
                applyFilters();
                return true;
            case R.id.day_all:
                currentDayFilter = -1;
                applyFilters();
                return true;
            case R.id.dist_1:
                currentDistanceFilter = 1;
                applyFilters();
                return true;
            case R.id.dist_3:
                currentDistanceFilter = 3;
                applyFilters();
                return true;
            case R.id.dist_5:
                currentDistanceFilter = 5;
                applyFilters();
                return true;
            case R.id.dist_10:
                currentDistanceFilter = 10;
                applyFilters();
                return true;
            case R.id.dist_all:
                currentDistanceFilter = -1;
                applyFilters();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        map.setMyLocationEnabled(true);
        double longitude = -1;
        double latitude = -1;
        final DaoActos DA = new DaoActos(this);
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {
            ApiManager am = new ApiManager();
            am.getApiService().getRequest(new Callback<Request>() {
                @Override
                public void success(Request resultado, Response response) {

                    List<Acto> actos = resultado.getResult();
                    mActos = actos;
                    applyFilters();
                }

                @Override
                public void failure(RetrofitError error) {
                }
            });
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent i = new Intent(getApplicationContext(), DetallesActivity.class);
                i.putExtra("id", Integer.parseInt(marker.getSnippet()));
                startActivity(i);
            }
        });

        //Place the Map View nearby Zaragoza
        map.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLngBounds(
                new LatLng(41.62827478, -0.97400665),
                new LatLng(41.6698344, -0.83255768))).getCenter()
                , 12));
    }

    public void applyFilters() {
        mFiltered = new ArrayList<>(mActos);
        filterByDistance(mFiltered, currentDistanceFilter);
        filterByDay(mFiltered, currentDayFilter);
        fillMapWithMarkers(mFiltered, mMap);
        showSnackbar(currentDistanceFilter, currentDayFilter);
    }

    public void filterByDistance(List<Acto> list, int km) {
        if (km > 0 && mLocation != null) {
            Iterator it = list.iterator();
            float[] results = new float[]{99999, 99999, 99999};
            while (it.hasNext()) {
                Acto a = (Acto) it.next();
                Location.distanceBetween(mLocation.getLatitude(), mLocation.getLongitude(), a.getLng(), a.getLat(), results);
                if (results[0] > km * 1000) {
                    it.remove();
                }
            }
        }
    }

    public void filterByDay(List<Acto> list, int day) {
        if (day > 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Acto a = (Acto) it.next();
                if ((a.getEndDate().getTime() % 2629743830L / 86400000L) != day) {
                    it.remove();
                }
            }
        }
    }

    public void fillMapWithMarkers(List<Acto> list, GoogleMap map) {
        if (map != null) {
            map.clear();
            for (Acto a : list) {
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(a.getLng(), a.getLat())).title(a.getTitle()).snippet(a.getId().toString()));
            }
        }
    }

    private void showSnackbar(int currentDistanceFilter, int currentDayFilter) {
        Snackbar.make(mapFragment.getView(), "Distancia: " + currentDistanceFilter + " | Dia: " + currentDayFilter, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
