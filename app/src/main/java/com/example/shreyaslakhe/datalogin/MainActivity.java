package com.example.shreyaslakhe.datalogin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DataHandler db = new DataHandler(this);
        final EditText first_name = (EditText) findViewById(R.id.editText);
        final EditText middle_name = (EditText) findViewById(R.id.editText2);
        final EditText last_name = (EditText) findViewById(R.id.editText3);
        final EditText mobile = (EditText) findViewById(R.id.editText4);
        final EditText address = (EditText) findViewById(R.id.editText5);
        Button save = (Button) findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(first_name.length() <= 0 || middle_name.length() <= 0 || last_name.length() <= 0 ||
                        mobile.length() <= 0 || address.length() <= 0)
                    Toast.makeText(getApplicationContext(), "All fields are compulsory", Toast.LENGTH_LONG).show();
                else {
                    Info info = new Info();
                    info.setFirst_name(first_name.getText().toString());
                    info.setMiddle_name(middle_name.getText().toString());
                    info.setLast_name(last_name.getText().toString());
                    info.setAddress(address.getText().toString());
                    info.setMobile(mobile.getText().toString());
                    db.addInfo(info);
                }
            }
        });

        Button next = (Button) findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), check.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
