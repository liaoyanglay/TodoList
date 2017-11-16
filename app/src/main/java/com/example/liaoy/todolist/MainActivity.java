package com.example.liaoy.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Incident> incidentList=new ArrayList<>();
    private RecyclerView recyclerView;
    private IncidentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        recyclerView=(RecyclerView)findViewById(R.id.incident_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new IncidentAdapter(incidentList);
        recyclerView.setAdapter(adapter);
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
        }
    }

    public void initial(){
        Incident incident1=new Incident("THE FIRST","I want to do");
        incidentList.add(incident1);
        Incident incident2=new Incident("THE SECOND","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        incidentList.add((incident2));
    }
}
