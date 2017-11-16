package com.example.liaoy.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText inputTitle;
    private EditText inputContent;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        save=(Button)findViewById(R.id.save);
        cancel=(Button)findViewById(R.id.cancel);
        inputContent=(EditText)findViewById(R.id.edit_content);
        inputTitle=(EditText)findViewById(R.id.edit_title);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=inputTitle.getText().toString();
                String content=inputContent.getText().toString();
                if(!"".equals(title)){
                    Incident incident=new Incident(title,content);
                    Intent intent=new Intent();
                    intent.putExtra("incident_return",incident);
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
}
