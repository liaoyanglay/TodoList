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

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.ViewHolder> {
    private List<Incident> mIncidentList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView title;
        TextView content;

        public ViewHolder(View view){
            super(view);
            layout=(LinearLayout) view.findViewById(R.id.inctable);
            title=(TextView) view.findViewById(R.id.incident_title);
            content=(TextView) view.findViewById(R.id.incident_content);
        }
    }

    public IncidentAdapter(List<Incident> incidentList){
        mIncidentList=incidentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.incident_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Incident incident=mIncidentList.get(position);
        holder.layout.setVisibility(View.VISIBLE);
        holder.title.setText(incident.getTitle());
        holder.content.setText(incident.getContent());
    }

    @Override
    public int getItemCount() {
        return mIncidentList.size();
    }
}
