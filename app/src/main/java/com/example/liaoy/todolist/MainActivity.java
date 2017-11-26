package com.example.liaoy.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Incident> incidentList;
    private RecyclerView recyclerView;
    private IncidentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();
        incidentList= DataSupport.findAll(Incident.class);
        recyclerView = findViewById(R.id.incident_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new IncidentAdapter(incidentList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new IncidentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Incident incident=incidentList.get(position);
                Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("incident",incident);
                intent.putExtra("position",position);
                intent.putExtra("status",1);
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_incident:
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("status",0);
                intent.putExtra("position",incidentList.size());

                startActivityForResult(intent,1);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    Incident returnedIncident= (Incident) data.getSerializableExtra("incident_return");

                    incidentList.add(returnedIncident);
                    adapter.notifyItemInserted(incidentList.size()-1);
                    Toast.makeText(MainActivity.this,"Successfully added",Toast.LENGTH_SHORT).show();
                }
                break;
            case  2:
                if(resultCode==RESULT_OK){
                    Incident returnedIncident= (Incident) data.getSerializableExtra("incident_return");

                    int position = data.getIntExtra("position",0);
                    incidentList.set(position,returnedIncident);
                    adapter.notifyItemChanged(position);
                    Toast.makeText(MainActivity.this,"Successfully modified",Toast.LENGTH_SHORT).show();
                }else if (resultCode==RESULT_FIRST_USER){
                    Incident returnedIncident= (Incident) data.getSerializableExtra("incident_return");
                    int position = data.getIntExtra("position",0);
                    incidentList.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position,incidentList.size()-position+1);
                }else if (resultCode==RESULT_CANCELED){}
        }
    }
}


