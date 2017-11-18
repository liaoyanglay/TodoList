package com.example.liaoy.todolist;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Created by liaoy on 2017/11/12.
 */

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.ViewHolder> implements View.OnClickListener{
    private List<Incident> mIncidentList;
    private OnItemClickListener mOnItemClickListener = null;

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView title;
        TextView content;

        public ViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.inctable);
            title = view.findViewById(R.id.incident_title);
            content = view.findViewById(R.id.incident_content);
        }
    }

    public IncidentAdapter(List<Incident> incidentList) {
        mIncidentList = incidentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.incident_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Incident incident = mIncidentList.get(position);
        holder.layout.setVisibility(View.VISIBLE);
        holder.title.setText(incident.getTitle());
        holder.content.setText(incident.getContent());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mIncidentList.size();
    }

    public interface OnItemClickListener{
        void onItemClick (View view,int position);
    }

    public  void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v){
        if(mOnItemClickListener!=null) {
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

}
