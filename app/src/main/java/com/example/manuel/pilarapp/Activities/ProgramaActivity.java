package com.example.manuel.pilarapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.manuel.pilarapp.ApiManager;
import com.example.manuel.pilarapp.Database.DaoActos;
import com.example.manuel.pilarapp.Database.DatabaseManager;
import com.example.manuel.pilarapp.Fragments.ProgramaFragment;
import com.example.manuel.pilarapp.Objects.Request;
import com.example.manuel.pilarapp.R;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.log.CustomLogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public class ProgramaActivity extends AppCompatActivity implements java.io.Serializable {

    public static final long COMIENZO_PILAR = 1444392000000L; // 9 de octubre a las 12 del mediodía
    public static final long FINAL_PILAR = 1445169600000L; // 18 de octubre a las 12 del mediodía
    public static final long DIA_EN_MS = 86400000L;
    private DaoActos DA;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    JobManager jobManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        configureJobManager();
        this.DA = new DaoActos(this);
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();

        checkDB();
    }

    private void configureJobManager() {
        Configuration configuration = new Configuration.Builder(this)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";

                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }
                })
                .minConsumerCount(0)
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minute
                .build();
        this.jobManager = new JobManager(this, configuration);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        for (long l = COMIENZO_PILAR; l <= FINAL_PILAR; l += DIA_EN_MS) {
            adapter.addFragment(ProgramaFragment.newInstance(l), getTabTitle(l));
        }
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
    }

    private String getTabTitle(long l) {
        SimpleDateFormat sdf = new SimpleDateFormat("cccc d", new Locale("es", "ES"));
        return sdf.format(new Date(l));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_programa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_ver_mapa) {
            startMapActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkDB() {
        //Check if DB update needed
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            Log.d("TAG", "Checking connection");
            ApiManager.getApiService().getHeaders(new Callback<Request>() {
                @Override
                public void success(Request request, Response response) {
                    //Update de la BD en caso de que haya sido modificada
                    Log.d("TAG", "Updating DB if needed");
                    List<Header> headerList = response.getHeaders();
                    for (Header header : headerList) {

                        Log.d("TAG header", header.toString());
                        //if (header.toString().contains("Last-Modified:")) {
                        //if (header.toString().substring(14, header.toString().length())
                        //!= "/*VALOR DE PREFERENCES*/") {
                        ApiManager.getApiService().getRequest(new Callback<Request>() {
                            @Override
                            public void success(Request request, Response response) {
                                jobManager.addJobInBackground(new DatabaseManager(DA, request.getResult()));
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                        // }
                        //}
                        break;
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("TAG", "Request fallida");
                    //Snackbar.make(mRecyclerView, "Error de conexión", Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    private void startMapActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }


        @Override
        public Fragment getItem(int position) {
            Log.d("TAG", position + " ");
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
