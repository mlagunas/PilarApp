package com.example.manuel.pilarapp;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manuel.pilarapp.Objects.Acto;

import java.util.List;

/**
 * Created by Gerard on 30/9/2015.
 */
public class ProgramaAdapter extends RecyclerView.Adapter<ProgramaAdapter.ViewHolder> implements View.OnClickListener {

    private OnItemClickListener onItemClickListener;

    private SortedList<Acto> mDataset;

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
        if (acto.getDescription() != null) {
            holder.secondaryText.setText(Html.fromHtml(acto.getDescription()));
        }
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

        public View root;
        public TextView primaryText;
        public TextView secondaryText;
        public TextView support;

        public ViewHolder(View v) {
            super(v);
            root = v.findViewById(R.id.root);
            primaryText = (TextView) v.findViewById(R.id.textPrimary);
            secondaryText = (TextView) v.findViewById(R.id.textSecondary);
            //support = (TextView) v.findViewById(R.id.support);
        }
    }

}