package com.pilarapp.manuel.pilarapp;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pilarapp.manuel.pilarapp.Objects.Acto;

import java.util.List;

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
            @Override
            public int compare(Acto o1, Acto o2) {
                if ((o1.getHoraInicio() == null || o1.getHoraInicio().isEmpty()) && (o2.getHoraInicio() == null || o2.getHoraInicio().isEmpty())) {
                    return 0;
                }
                if (o1.getHoraInicio() == null || o1.getHoraInicio().isEmpty()) {
                    return 1;
                }
                if (o2.getHoraInicio() == null || o2.getHoraInicio().isEmpty()) {
                    return -1;
                }
                return o1.getHoraInicio().compareTo(o2.getHoraInicio());
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
        if ((acto.getHoraInicio() != null && !acto.getHoraInicio().equals(""))
                && (acto.getHoraFinal() != null && !acto.getHoraFinal().equals(""))) {
            holder.secondaryText.setText(acto.getHoraInicio() + "-" + acto.getHoraFinal());
        } else if (acto.getHoraInicio() != null && !acto.getHoraInicio().equals("")) {
            holder.secondaryText.setText(acto.getHoraInicio());
        } else if (acto.getHorario() != null && !acto.getHorario().isEmpty()) {
            holder.secondaryText.setText("Acto con horario irregular");
        } else {
            holder.secondaryText.setText("Acto sin hora especificada");
        }
        if (acto.getAddress() != null && !acto.getAddress().isEmpty() &&
                !acto.getAddress().trim().equals("null"))
            holder.thirdText.setText(acto.getAddress());
        else
            holder.thirdText.setText("No existe una dirección especificiada");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset != null ? mDataset.size() : 0;
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
            thirdText = (TextView) v.findViewById(R.id.textThird);
            //support = (TextView) v.findViewById(R.id.support);
        }
    }

}