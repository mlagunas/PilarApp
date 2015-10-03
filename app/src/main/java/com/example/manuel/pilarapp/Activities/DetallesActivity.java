package com.example.manuel.pilarapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manuel.pilarapp.ApiManager;
import com.example.manuel.pilarapp.Database.DaoActos;
import com.example.manuel.pilarapp.Objects.Acto;
import com.example.manuel.pilarapp.R;
import com.facebook.share.model.ShareLinkContent;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetallesActivity extends AppCompatActivity {

    private Acto mActo;
    private static NetworkInfo activeNetwork;
    private static ConnectivityManager cm;
    private ImageView headerView;
    private TextView titleView;
    private TextView subtitleView;
    private TextView contentView;
    private DaoActos DA;
    private FloatingActionButton fab;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        DA = new DaoActos(this);

        cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();


        headerView = (ImageView) findViewById(R.id.header);
        titleView = (TextView) findViewById(R.id.title);
        subtitleView = (TextView) findViewById(R.id.subtitle);
        contentView = (TextView) findViewById(R.id.content);
        fab = (FloatingActionButton) findViewById(R.id.action_share);

        int id = getIntent().getIntExtra("id", 0);
        requestActo(id);
    }

    private void requestActo(int id) {
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            ApiManager.getApiService().getActo(id, new Callback<Acto>() {
                @Override
                public void success(Acto acto, Response response) {
                    mActo = acto;
                    titleView.setText(acto.getTitle());
                    if (acto.getDescription() != null) {
                        contentView.setText(Html.fromHtml(acto.getDescription()));
                    }
                    setupHeaderImage(acto.getLat(false), acto.getLng(false));
                    setupFab(acto.getTitle());
                }
                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
        else{
            mActo = DA.getActo(id);
            if (mActo != null) {
                titleView.setText(mActo.getTitle());
                if (mActo.getDescription() != null) {
                    contentView.setText(Html.fromHtml(mActo.getDescription()));
                }
                setupHeaderImage(mActo.getLat(false), mActo.getLng(false));
            }
        }
    }

    private void setupHeaderImage(final double lat, final double lng) {
        if (lat == -1 && lng == -1){
            Glide.with(this)
                    .load("https://maps.googleapis.com/maps/api/staticmap?center=41.6548748,-0.8806778&zoom=14&size=720x400&markers=color:red%7Clabel:S%7C"+lng+","+lat)
                    .into(headerView);
            return;
        }

        Glide.with(this)
                .load("https://maps.googleapis.com/maps/api/staticmap?center="+lng+","+lat+"&zoom=17&size=720x400&markers=color:red%7Clabel:S%7C"+lng+","+lat)
                .into(headerView);

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:"+lng+","+lat+"?q="+lng+","+lat+"?z=17");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    public void setupFab(final String title){
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, title);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
