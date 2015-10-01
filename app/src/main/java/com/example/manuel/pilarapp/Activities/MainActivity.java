package com.example.manuel.pilarapp.Activities;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.example.manuel.pilarapp.ApiManager;
import com.example.manuel.pilarapp.Objects.Acto;
import com.example.manuel.pilarapp.Objects.Request;
import com.example.manuel.pilarapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.drawer_item_compact_header);

        // Create a few sample profile
        final IProfile profile = new ProfileDrawerItem().withName("Fiestas del Pilar 2015");

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(false)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_free_play).withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_custom).withIcon(FontAwesome.Icon.faw_eye),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withSavedInstance(savedInstanceState)
                .build();
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

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onMapReady(GoogleMap map) {

        double longitude = -1;
        double latitude = -1;

        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {
            ApiManager am = new ApiManager();
            am.getApiService().getRequest(new Callback<Request>() {
                @Override
                public void success(Request resultado, Response response) {

                    List<Header> headerList = response.getHeaders();
                    for (Header header : headerList) {

                    }

                    List<Acto> actos = resultado.getResult();
                    for (Acto acto : actos) {
                        Log.d("TAG diasTermina",acto.getDiasParaTerminar()+" ");
                        Log.d("TAG title",acto.getTitle()+" ");
                        Log.d("TAG description",acto.getDescription()+" ");
                        Log.d("TAG image",acto.getImage()+" ");
                        Log.d("TAG entrada",acto.getPrecioEntrada()+" ");
                        Log.d("TAG startdate",acto.getStartDate()+" ");
                        Log.d("TAG enddate",acto.getEndDate()+" ");
                        Log.d("TAG id",acto.getId()+" ");
                        Log.d("TAG web",acto.getWeb()+" ");
                        Log.d("TAG prgrama",acto.getPrograma()+" ");
                        Log.d("TAG destacada",acto.getDestacada()+" ");
                        Log.d("TAG lat",acto.getLat()+" ");
                        Log.d("TAG lng",acto.getLng()+" ");
                        Log.d("TAG tipo ent",acto.getTipoEntrada()+" ");
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.v("TAG", "Error: " + error.getMessage() + error.getStackTrace());
                }
            });
        }
        final Marker melbourne = map.addMarker(new MarkerOptions()
                .position(new LatLng(41.655556011465016,-0.9064338869860967))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        //Place the Map View nearby Zaragoza
        map.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLngBounds(
                new LatLng(41.62827478, -0.97400665),
                new LatLng(41.6698344, -0.83255768))).getCenter()
                , 12));
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }


}
