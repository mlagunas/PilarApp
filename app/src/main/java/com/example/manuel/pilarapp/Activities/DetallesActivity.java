package com.example.manuel.pilarapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
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

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public class DetallesActivity extends AppCompatActivity {

    private Acto mActo;
    private static NetworkInfo activeNetwork;
    private static ConnectivityManager cm;
    private ImageView headerView;
    private TextView titleView;
    private TextView subtitleView;
    private TextView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar(toolbar);

        cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();


        headerView = (ImageView) findViewById(R.id.header);
        titleView = (TextView) findViewById(R.id.title);
        subtitleView = (TextView) findViewById(R.id.subtitle);
        contentView = (TextView) findViewById(R.id.content);

        int id = getIntent().getIntExtra("id", 0);
        requestActo(id);
    }

    private void requestActo(int id) {
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            ApiManager.getApiService().getActo(id, new Callback<Acto>() {
                @Override
                public void success(Acto acto, Response response) {
//                    if (acto.getPrograma() != null) {
//                    subtitleView.setText(acto.getPrograma());
//                }
/*                   Log.d("TAG", acto.getDiasParaTerminar() + " ");
                    Log.d("TAG", acto.getTitle() + " ");
                    Log.d("TAG", acto.getDescription() + " ");
                    Log.d("TAG", acto.getImage() + " ");
                    Log.d("TAG", acto.getPrecioEntrada() + " ");
                    Log.d("TAG", acto.getStartDate().toString() + " ");
                    Log.d("TAG", acto.getEndDate().toString() + " ");
                    Log.d("TAG", acto.getId() + " ");
                    Log.d("TAG", acto.getWeb() + " ");
                    Log.d("TAG", acto.getPrograma() + " ");
                    Log.d("TAG", acto.getDestacada() + " ");
                    Log.d("TAG", acto.getLat(false) + " ");
                    Log.d("TAG", acto.getLng(false) + " ");
                    Log.d("TAG", acto.getTipoEntrada() + " ");*/

                    mActo = acto;
                    titleView.setText(acto.getTitle());
                    if (acto.getDescription() != null) {
                        contentView.setText(Html.fromHtml(acto.getDescription()));
                    }
                    setupHeaderImage(acto.getLat(false), acto.getLng(false));
                }
                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
        else{
            Log.d("TAG BD","GETDATOS DESDE BD");
            mActo = new DaoActos(this).getActo(id);
            titleView.setText(mActo.getTitle());
            if (mActo.getDescription() != null) {
                contentView.setText(Html.fromHtml(mActo.getDescription()));
            }
            setupHeaderImage(mActo.getLat(false), mActo.getLng(false));
        }
    }

    private void setupHeaderImage(final double lat, final double lng) {
        //TODO - Tamanno de la imagen dinamico
        Glide.with(this)
                .load("https://maps.googleapis.com/maps/api/staticmap?center="+lng+","+lat+"&zoom=17&size=720x400&markers=color:blue%7Clabel:S%7C"+lng+","+lat)
                .into(headerView);

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:" + lng + "," + lat + "+?z=17");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
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
