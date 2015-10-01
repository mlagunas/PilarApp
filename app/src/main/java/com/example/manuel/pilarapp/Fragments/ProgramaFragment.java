package com.example.manuel.pilarapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manuel.pilarapp.Activities.DetallesActivity;
import com.example.manuel.pilarapp.ApiManager;
import com.example.manuel.pilarapp.Database.DaoActos;
import com.example.manuel.pilarapp.Objects.Acto;
import com.example.manuel.pilarapp.Objects.Request;
import com.example.manuel.pilarapp.ProgramaAdapter;
import com.example.manuel.pilarapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public class ProgramaFragment extends Fragment implements ProgramaAdapter.OnItemClickListener{
    private static final String ARG_DIA = "dia_de_las_fiestas";

    private long mDia;
    private RecyclerView mRecyclerView;
    private ProgramaAdapter mAdapter;
    private Boolean connection;
    private static NetworkInfo activeNetwork;
    private static ConnectivityManager cm;

    public static ProgramaFragment newInstance(long dia) {
        ProgramaFragment fragment = new ProgramaFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_DIA, dia);
        fragment.setArguments(args);



        return fragment;
    }

    public ProgramaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cm = (ConnectivityManager)this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        if (getArguments() != null) {
            mDia = getArguments().getLong(ARG_DIA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_programa, container, false);
        setupRecyclerView(mRecyclerView);
        return mRecyclerView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestProgram();
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        mAdapter = new ProgramaAdapter(R.layout.row_programa);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnFeedItemClickListener(this);
    }

    private void requestProgram() {
        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            Log.d("TAG", "Request iniciada");
            ApiManager.getApiService().getRequest(getRequestQuery(mDia), new Callback<Request>() {
                @Override
                public void success(Request request, Response response) {

                    //Update de la BD en caso de que haya sido modificada
                    List<Header> headerList = response.getHeaders();
                    for (Header header : headerList) {
                        if (header.toString().regionMatches(0,"Last-Modified:",0,14)){
                            if ( header.toString().substring(14,header.toString().length())
                                    != "/*VALOR DE PREFERENCES*/"){
                                DaoActos DA = new DaoActos(getContext());
                                DA.truncateDB();;
                                DA.fillDB(request.getResult());
                            }
                            Log.d("TAG header", header.toString());
                        }
                    }
                    Log.d("TAG", "Request completada");
                    mAdapter.setData(request.getResult());

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("TAG", "Request fallida");
                    Snackbar.make(mRecyclerView, "Error de conexión", Snackbar.LENGTH_LONG).show();
                }
            });
        }
        else {
            Log.d("TAG BD","GETDATOS DESDE BD");
            mAdapter.setData(new DaoActos(getContext()).getActos());
        }
    }

    private String getRequestQuery(long l) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "programa==fiestas del pilar;startDate=="+sdf.format(new Date(l))+"T00:00:00Z";
    }

    @Override
    public void onItemClick(View v, Acto acto) {
        Intent i = new Intent(getActivity(), DetallesActivity.class);
        i.putExtra("id", acto.getId());
        startActivity(i);
    }
}
