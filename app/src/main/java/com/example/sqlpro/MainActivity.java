package com.example.sqlpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,password;
    dbAdapter helper;
//    Button login;
    Button signup;
    TextView swtichMethod;
     int choice=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //This will tell if the user wants to login or signup
        //1 - signup
        //2 - login
        Intent i = new Intent(getApplicationContext(), MainActivity2.class);
        name=(EditText) findViewById(R.id.editTextTextEmailAddress);
        password=(EditText) findViewById(R.id.editTextTextPassword);
        helper=new dbAdapter(this);
        signup=findViewById(R.id.button);
        swtichMethod= (TextView) findViewById(R.id.textView);

        swtichMethod.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(choice==1) {
                    swtichMethod.setText("or signup instead");
                    signup.setText("LOGIN");
                    choice=2;
                }else
                {
                    choice=1;
                    swtichMethod.setText("or login instead");
                    signup.setText("SIGNUP");
                }


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choice==1)
                {
                    String t1 = name.getText().toString();
                    String t2 = password.getText().toString();
                    if(t1.isEmpty() || t2.isEmpty())
                    {
                        Toast.makeText(MainActivity.this, "Enter Both Email and Password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        boolean check=helper.checkData(t1,t2);
                        if(check)
                        {
                            Toast.makeText(MainActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            long id = helper.insertData(t1,t2);
                            if(id<=0)
                            {
                                Toast.makeText(MainActivity.this, "Insetion Unsuccessful", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                password.setText("");
                            } else
                            {
                                Toast.makeText(MainActivity.this, "Insertion Successful", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                password.setText("");
                                startActivity(i);
                            }
                        }
                    }
                }
                else
                {
                    String t1 = name.getText().toString();
                    String t2 = password.getText().toString();
                    if(t1.isEmpty() || t2.isEmpty())
                    {
                        Toast.makeText(MainActivity.this, "Enter Both Email and Password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        boolean check=helper.checkPassword(t1,t2);
                        if(check)
                        {
                            Toast.makeText(MainActivity.this, "User Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


    }

}