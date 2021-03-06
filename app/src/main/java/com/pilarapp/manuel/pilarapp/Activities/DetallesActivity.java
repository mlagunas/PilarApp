package com.pilarapp.manuel.pilarapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pilarapp.manuel.pilarapp.ApiManager;
import com.pilarapp.manuel.pilarapp.Database.DaoActos;
import com.pilarapp.manuel.pilarapp.Objects.Acto;
import com.pilarapp.manuel.pilarapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DetallesActivity extends AppCompatActivity {

    private Acto mActo;
    private static NetworkInfo activeNetwork;
    private static ConnectivityManager cm;
    private ImageView headerView;
    private TextView dateView;
    private TextView titleView;
    private TextView contentView;
    private TextView precioView;
    private TextView horarioView, horarioTituloView;
    private TextView temaView;
    private ImageView eventImage;
    private DaoActos DA;
    private FloatingActionButton fab;
    private long date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar(toolbar);
        DA = new DaoActos(this);

        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();


        headerView = (ImageView) findViewById(R.id.header);
        dateView = (TextView) findViewById(R.id.date);
        titleView = (TextView) findViewById(R.id.title);
        contentView = (TextView) findViewById(R.id.content);
        precioView = (TextView) findViewById(R.id.precio);
        horarioView = (TextView) findViewById(R.id.horario);
        horarioTituloView = (TextView) findViewById(R.id.horarioTitulo);
        temaView = (TextView) findViewById(R.id.tema);
        eventImage = (ImageView) findViewById(R.id.eventImage);

        fab = (FloatingActionButton) findViewById(R.id.action_share);

        date = getIntent().getLongExtra("date", 0);
        int id = getIntent().getIntExtra("id", 0);
        requestActo(id);
    }

    private void requestActo(int id) {
        if (!DA.hasItems()) {
            ApiManager.getApiService().getActo(id, new Callback<Acto>() {
                @Override
                public void success(Acto acto, Response response) {
                    mActo = acto;
                    setupUI(acto);

                }

                @Override
                public void failure(RetrofitError error) {
                }
            });
        } else {
            Acto acto = DA.getActo(id);
            mActo = acto;
            setupUI(acto);

        }
    }

    private void setupUI(Acto acto) {
        titleView.setText(acto.getTitle());
        if (acto.getDescription() != null) {
            contentView.setText(stringFromHtml(acto.getDescription()));
        } else {
            contentView.setText("Descripcion no establecida");
        }
        setupHorario(acto, horarioView, horarioTituloView);
        setupDateText(acto, dateView);
        setupHeaderImage(acto.getLat(), acto.getLng());

        if (acto.getPrecioEntrada() != null && !acto.getPrecioEntrada().isEmpty()) {
            precioView.setText(stringFromHtml(acto.getPrecioEntrada()));
        } else if (acto.getTipoEntrada() != null && !acto.getTipoEntrada().isEmpty()) {
            precioView.setText(stringFromHtml(acto.getTipoEntrada()));
        } else {
            precioView.setText("Precio no establecido");
        }
        temaView.setText(acto.getTema());
        setupFab(acto.getTitle());
        Glide.with(this).load("http:" + acto.getImagen()).into(eventImage);
    }

    private void setupHorario(Acto acto, TextView horarioView, TextView horarioTituloView) {
        if ((acto.getHoraInicio() == null || acto.getHoraInicio().isEmpty()) && acto.getHorario() != null && !acto.getHorario().isEmpty()) {
            horarioTituloView.setVisibility(View.VISIBLE);
            horarioView.setText(stringFromHtml(acto.getHorario()));
            horarioView.setVisibility(View.VISIBLE);
        }
    }

    private void setupDateText(Acto acto, TextView dateView) {
        SimpleDateFormat sdf = new SimpleDateFormat("cccc d", new Locale("es", "ES"));
        String dateString = sdf.format(new Date(date)) + " | ";
        if ((acto.getHoraInicio() != null && !acto.getHoraInicio().isEmpty())
                && (acto.getHoraFinal() != null && !acto.getHoraFinal().isEmpty())) {
            dateString += acto.getHoraInicio() + "-" + acto.getHoraFinal();
        } else if (acto.getHoraInicio() != null && !acto.getHoraInicio().isEmpty()) {
            dateString += acto.getHoraInicio();
        }
        dateView.setText(dateString);
    }

    private void setupHeaderImage(final double lat, final double lng) {
        if (lat == -1 && lng == -1) {
            Glide.with(this)
                    .load("https://maps.googleapis.com/maps/api/staticmap?center=41.6548748,-0.8806778&zoom=14&size=720x400&markers=color:red%7Clabel:S%7C" + lng + "," + lat)
                    .placeholder(R.drawable.chachirulo)
                    .into(headerView);
            return;
        }

        Glide.with(this)
                .load("https://maps.googleapis.com/maps/api/staticmap?center=" + lng + "," + lat + "&zoom=17&size=720x400&markers=color:red%7Clabel:S%7C" + lng + "," + lat)
                .placeholder(R.drawable.chachirulo)
                .into(headerView);

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:" + lng + "," + lat + "?q=" + lng + "," + lat + "?z=17");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    public void setupFab(final String title) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, title);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Compartir en ..."));
            }
        });
    }

    private void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    public static String stringFromHtml(String html) {
        if (html == null)
            return html;
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
        document.select("br").append("\\n");
        document.select("p").prepend("\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)).trim().replaceAll("[ ]{2,}", "").replaceAll("&nbsp;", "");
    }
}
