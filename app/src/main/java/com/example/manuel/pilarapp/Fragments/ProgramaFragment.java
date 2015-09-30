package com.example.manuel.pilarapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manuel.pilarapp.ApiManager;
import com.example.manuel.pilarapp.Objects.Request;
import com.example.manuel.pilarapp.ProgramaAdapter;
import com.example.manuel.pilarapp.R;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProgramaFragment extends Fragment {
    private static final String ARG_DIA = "dia_de_las_fiestas";

    private String mDia;
    private RecyclerView mRecyclerView;
    private ProgramaAdapter mAdapter;

    public static ProgramaFragment newInstance(String dia) {
        ProgramaFragment fragment = new ProgramaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DIA, dia);
        fragment.setArguments(args);
        return fragment;
    }

    public ProgramaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDia = getArguments().getString(ARG_DIA);
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
    }

    private void requestProgram() {
        Log.d("TAG","Request iniciada");
        ApiManager.getApiService().getRequest(new Callback<Request>() {
            @Override
            public void success(Request request, Response response) {
                Log.d("TAG","Request completada");
                mAdapter.setData(request.getResult());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("TAG","Request fallida");
                Snackbar.make(mRecyclerView, "Error de conexión", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}