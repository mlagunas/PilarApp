package com.example.manuel.pilarapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.manuel.pilarapp.Objects.Actos;
import com.example.manuel.pilarapp.Objects.Request;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,
                container, false);
        Button button = (Button) view.findViewById(R.id.buttonGet);
        final TextView textview = (TextView) view.findViewById((R.id.textView));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText("Botón pulsado");
                ApiManager am = new ApiManager();
                am.getApiService().getRequest(new Callback<Request>() {
                    @Override
                    public void success(Request resultado, Response response) {
                        textview.setText("consulta con exito");
                        List<Actos> actos = resultado.getResult();
                        for (Actos a :actos){
                            Log.d("DATA", a.getDescription());
                            Log.d("DATA", a.getPrograma());
                            Log.d("DATA", a.getEndDate());
                            Log.d("DATA", a.getStartDate());
                            Log.d("DATA", a.getTitle());
                            Log.d("DATA", a.getEntidad().getTitle());
                            Log.d("DATA", a.getGeometry().getCoordinates().toString());
                            Log.d("DATA", a.getDiasParaTerminar()+" ");
                            Log.d("DATA", a.getLugarInscripcion().getRequisitosInscrip());

                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v("TAG", "Error: " + error.getMessage() + error.getStackTrace());
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle state)
    {
        super.onActivityCreated(state);





    }
}
