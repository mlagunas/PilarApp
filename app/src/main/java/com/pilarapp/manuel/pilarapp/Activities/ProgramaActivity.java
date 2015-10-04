package com.pilarapp.manuel.pilarapp.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import android.view.View;
import android.widget.ProgressBar;

import com.pilarapp.manuel.pilarapp.ApiManager;
import com.pilarapp.manuel.pilarapp.Database.DaoActos;
import com.pilarapp.manuel.pilarapp.Fragments.ProgramaFragment;
import com.pilarapp.manuel.pilarapp.Objects.Request;
import com.pilarapp.manuel.pilarapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;


public class ProgramaActivity extends AppCompatActivity {

    public static final long COMIENZO_PILAR = 1444392000000L; // 9 de octubre a las 12 del mediodía
    public static final long FINAL_PILAR = 1445169600000L; // 18 de octubre a las 12 del mediodía
    public static final long DIA_EN_MS = 86400000L;
    private DaoActos DA;
    private ConnectivityManager cm;
    private NetworkInfo activeNetwork;
    private SpotsDialog sp;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.DA = new DaoActos(this);

        //Check if DB update needed
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();

        final SharedPreferences mPrefs = getSharedPreferences("Last-modified", 0);
        final SharedPreferences.Editor editor = mPrefs.edit();


        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            sp = new SpotsDialog(this,R.style.Custom);
            sp.setCancelable(false);
            sp.show();
            ApiManager.getApiService().getHeaders(new Callback<Header>() {


                @Override
                public void success(Header headers, Response response) {
                    //Update de la BD en caso de que haya sido modificada  06c31b7c8b567090d821274cc660cf127
                    List<Header> headerList = response.getHeaders();
                    for (Header header : headerList) {
                        if (header.getName() != null
                                && header.getName().equals("ETag")) {
                            final String newDate = header.getValue();
                            if (!newDate.equals(mPrefs.getString("Last-modified",""))) {
                                editor.putString("Last-modified", newDate).commit();
                                updateDB();
                            } else {
                                setSupportActionBar(toolbar);
                                setupViewPager(mViewPager);
                                tabLayout.setupWithViewPager(mViewPager);
                            }
                            break;
                        }
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Snackbar.make(mViewPager, "Error de conexión", Snackbar.LENGTH_LONG).show();
                }

                private void updateDB() {
                    ApiManager.getApiService().getRequest(new Callback<Request>() {
                        @Override
                        public void success(final Request request, Response response) {
                            List<Header> headerList = response.getHeaders();
                            for (Header header : headerList) {
                                Log.d("TAG", header.getName() + header.getValue());
                            }
                            DA.truncateDB();
                            DA.fillDB(request.getResult(), false);

                            setSupportActionBar(toolbar);
                            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                            setupViewPager(viewPager);
                            tabLayout.setupWithViewPager(viewPager);
                            stopDialog();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Snackbar.make(mViewPager, "Error de conexión", Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            });
        };
    }

    private void stopDialog(){
        sp.hide();
        sp.dismiss();
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        for (long l = COMIENZO_PILAR; l <= FINAL_PILAR; l += DIA_EN_MS) {
            mAdapter.addFragment(ProgramaFragment.newInstance(l), getTabTitle(l));
        }
        viewPager.setOffscreenPageLimit(2);

        Calendar cal = Calendar.getInstance();
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        if (mes == 9 && (dia >= 9 && dia <= 18)) {
            mViewPager.setCurrentItem(dia - 9);
        }

        viewPager.setAdapter(mAdapter);
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

            cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            activeNetwork = cm.getActiveNetworkInfo();

            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                startMapActivity(mViewPager.getCurrentItem() + 9);
            } else {
                Snackbar.make(mViewPager, "Error de conexión", Snackbar.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void startMapActivity(int day) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("day", day);
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
