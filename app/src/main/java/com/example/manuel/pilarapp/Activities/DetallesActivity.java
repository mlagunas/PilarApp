package com.example.manuel.pilarapp.Activities;

import android.content.Intent;
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
import com.example.manuel.pilarapp.Objects.Acto;
import com.example.manuel.pilarapp.R;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetallesActivity extends AppCompatActivity {

    private Acto mActo;

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

        headerView = (ImageView) findViewById(R.id.header);
        titleView = (TextView) findViewById(R.id.title);
        subtitleView = (TextView) findViewById(R.id.subtitle);
        contentView = (TextView) findViewById(R.id.content);

        // TODO - Cargar imagen dinamicamente y no esto
        Glide.with(this)
                .load("https://maps.googleapis.com/maps/api/staticmap?center=41.6564447,-0.878618&zoom=17&size=720x400")
                .into(headerView);

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO - Cargar posicion dinamicamente
                Uri gmmIntentUri = Uri.parse("geo:41.6564447,-0.878618?z=17");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        int id = getIntent().getIntExtra("id", 0);
        requestActo(id);

    }

    private void requestActo(int id) {
        ApiManager.getApiService().getActo(id, new Callback<Acto>() {
            @Override
            public void success(Acto acto, Response response) {
                mActo = acto;
                titleView.setText(acto.getTitle());
//                if (acto.getPrograma() != null) {
//                    subtitleView.setText(acto.getPrograma());
//                }
                Log.d("TAG",acto.getDiasParaTerminar()+" ");
                Log.d("TAG",acto.getTitle()+" ");
                Log.d("TAG",acto.getDescription()+" ");
                Log.d("TAG",acto.getImage()+" ");
                Log.d("TAG",acto.getPrecioEntrada()+" ");
                Log.d("TAG",acto.getStartDate().toString()+" ");
                Log.d("TAG",acto.getEndDate().toString()+" ");
                Log.d("TAG",acto.getId()+" ");
                Log.d("TAG",acto.getWeb()+" ");
                Log.d("TAG",acto.getPrograma()+" ");
                Log.d("TAG",acto.getDestacada()+" ");
                Log.d("TAG",acto.getLat()+" ");
                Log.d("TAG",acto.getLng()+" ");
                Log.d("TAG",acto.getTipoEntrada()+" ");

                if (acto.getDescription() != null) {
                    contentView.setText(Html.fromHtml(acto.getDescription()));
                }
            }

            @Override
            public void failure(RetrofitError error) {

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
