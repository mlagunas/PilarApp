package com.example.manuel.pilarapp.Database;

import android.renderscript.RenderScript;
import android.util.Log;

import com.example.manuel.pilarapp.Objects.Acto;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Manuel on 03/10/2015.
 */
public class DatabaseManager extends Job implements java.io.Serializable {

    private DaoActos DA;
    private List<Acto> actos;

    public DatabaseManager(DaoActos da, List<Acto> actos){
        super(new Params(5).persist().groupBy("fillDB"));
        this.DA = da;
        this.actos = actos;
    }

    @Override
    public void onAdded() {
        Log.d("TAG", "Nuevo trabajo añadido");
        //Trabajo añadido
    }

    @Override
    public void onRun() throws Throwable {
        Log.d("TAG", "filling db");
        DA.fillDB(actos,false);

    }

    @Override
    protected void onCancel() {
        DA.truncateDB();
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
