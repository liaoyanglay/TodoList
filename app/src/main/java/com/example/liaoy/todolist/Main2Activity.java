package com.example.liaoy.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText inputTitle;
    private EditText inputContent;
    private Button save;
    private Button cancel;
    private int position;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        inputContent = findViewById(R.id.edit_content);
        inputTitle = findViewById(R.id.edit_title);
        intent=getIntent();
        if(intent.getStringExtra("status")!="add"){
            Incident incident = (Incident) intent.getSerializableExtra("incident");
            inputTitle.setText(incident.getTitle());
            inputContent.setText(incident.getContent());
            position = intent.getIntExtra("position", -1);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=inputTitle.getText().toString();
                String content=inputContent.getText().toString();
                if(!"".equals(title)){
                    Incident incident=new Incident(title,content);
                    Intent intent=new Intent();
                    intent.putExtra("incident_return",incident);
                    intent.putExtra("position",position);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                if( "".equals(title)){
                    Toast.makeText(Main2Activity.this,"TITLE can't be empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(getIntent().getStringExtra("status")!="add"){
            getMenuInflater().inflate(R.menu.edit,menu);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_incident:
                Intent intent=new Intent();
                intent.putExtra("position",position);
                setResult(RESULT_FIRST_USER,intent);
                finish();
                break;
            default:
        }
        return true;
    }
}
