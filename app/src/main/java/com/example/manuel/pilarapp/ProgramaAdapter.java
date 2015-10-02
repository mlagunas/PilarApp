package com.example.manuel.pilarapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manuel.pilarapp.Objects.Acto;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Gerard on 30/9/2015.
 */
public class ProgramaAdapter extends RecyclerView.Adapter<ProgramaAdapter.ViewHolder> implements View.OnClickListener {

    private OnItemClickListener onItemClickListener;

    private SortedList<Acto> mDataset;
    private Context context;
    private int layoutResource;

    public ProgramaAdapter(int layoutResource) {
        this.layoutResource = layoutResource;
        mDataset = new SortedList<>(Acto.class, new SortedListAdapterCallback<Acto>(this) {
            // TODO - Complete this class
            @Override
            public int compare(Acto o1, Acto o2) {
                return -1;
            }

            @Override
            public boolean areContentsTheSame(Acto oldItem, Acto newItem) {
                return false;
            }

            @Override
            public boolean areItemsTheSame(Acto item1, Acto item2) {
                return false;
            }
        });
    }

    public void setData(List<Acto> updates) {
        Log.d("TAG actos", updates.size()+" ");
        if (updates != null && !updates.isEmpty()) {
            mDataset.beginBatchedUpdates();
            mDataset.addAll(updates);
            mDataset.endBatchedUpdates();
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProgramaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutResource, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get elements from dataset
        Acto acto = mDataset.get(position);

        // Save position in tag and set onClickListener
        holder.root.setTag(acto);
        holder.root.setOnClickListener(this);

        // Replace contents of the view
        holder.primaryText.setText(acto.getTitle());
        if ((acto.getHoraInicio(false) != null && acto.getHoraInicio(false).trim() != "")
            && (acto.getHoraFinal(false)!= null && acto.getHoraFinal(false).trim() != "")) {
            holder.secondaryText.setText("Horario :" + acto.getHoraInicio(true)+"-"+acto.getHoraFinal(true));
        }
        else if (acto.getHoraInicio(false) != null && acto.getHoraInicio(false).trim() != ""){
            holder.secondaryText.setText("Hora de inicio: " + acto.getHoraInicio(true));
        }
        else{
            holder.secondaryText.setText("Acto sin hora especificada");
        }

        holder.thirdText.setText("Dirección: "+getAddress(acto.getLat(false), acto.getLng(false)));

        //holder.secondaryText.setText(DateUtils.getRelativeDateTimeString(App.getContext(), acto.getPublishedTime().getTime(), DateUtils.HOUR_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0));
        //holder.secondaryText.setText(DateUtils.formatDateTime(App.getContext(), acto.getPublishedTime().getTime(), 0));
        //holder.secondaryText.setText(acto.getPublishedTime());
//        if (holder.support != null) {
//            holder.support.setText(acto.getPrograma());
//        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset != null ? mDataset.size() : 0;
    }

    private String getAddress(double latitude,double longitude){
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            Log.d("TAG",latitude + " "+ longitude);
            addresses = geocoder.getFromLocation(longitude, latitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            if(addresses.size()>0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                Log.d("TAG",address+" - "+country+" - "+postalCode);
                    return address;

            }
            else
            {
                return "Direccion no especificada";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Direccion no especificada";
        }


    }

    public Acto getItem(int position) {
        if (mDataset == null || mDataset.size() == 0) {
            return null;
        } else {
            return mDataset.get(position);
        }
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, (Acto) v.getTag());
        }
    }

    public void setOnFeedItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, Acto acto);
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView thirdText;
        public View root;
        public TextView primaryText;
        public TextView secondaryText;
        public TextView support;

        public ViewHolder(View v) {
            super(v);
            root = v.findViewById(R.id.root);
            primaryText = (TextView) v.findViewById(R.id.textPrimary);
            secondaryText = (TextView) v.findViewById(R.id.textSecondary);
            thirdText = (TextView)  v.findViewById(R.id.textThird);
            //support = (TextView) v.findViewById(R.id.support);
        }
    }

}