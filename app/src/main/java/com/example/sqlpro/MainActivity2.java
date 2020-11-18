package com.example.sqlpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText updateold,updatenew,delete;
    dbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        updateold=findViewById(R.id.editTextTextEmailAddress5);
        updatenew=findViewById(R.id.editTextTextEmailAddress6);
        delete=findViewById(R.id.editTextText3);

        helper=new dbAdapter(this);


    }

    public void viewdata(View view)
    {
        String data = helper.getData();
//        Message.message(this,data);
        if(data.length()>0)
        {
            Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Nothing to show !!", Toast.LENGTH_LONG).show();
        }
    }

    public void update( View view)
    {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if(u1.isEmpty() || u2.isEmpty())
        {
//            Message.message(getApplicationContext(),"Enter Data");
            Toast.makeText(this, "Enter both the fields", Toast.LENGTH_LONG).show();
        }
        else
        {
            int a= helper.updateName( u1, u2);
            if(a<=0)
            {
//                Message.message(getApplicationContext(),"Unsuccessful");
                Toast.makeText(this, "Unsucessful", Toast.LENGTH_LONG).show();
                updateold.setText("");
                updatenew.setText("");
            } else {
//                Message.message(getApplicationContext(),"Updated");
                Toast.makeText(this, "Updated Sucessfully", Toast.LENGTH_SHORT).show();
                updateold.setText("");
                updatenew.setText("");
            }
        }

    }
    public void delete( View view)
    {
        String uname = delete.getText().toString();
        if(uname.isEmpty())
        {
            Toast.makeText(this, "Enter data", Toast.LENGTH_LONG).show();
        }
        else{
            int a= helper.delete(uname);
            if(a<=0)
            {
                Toast.makeText(this, "Unsucessful", Toast.LENGTH_LONG).show();
                delete.setText("");
            }
            else
            {
                Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show();
                delete.setText("");
            }
        }
    }

}