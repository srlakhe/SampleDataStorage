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


public class check extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        final DataHandler db = new DataHandler(this);
        final EditText enter_first_name = (EditText) findViewById(R.id.editTextsearch);
        final EditText first_name = (EditText) findViewById(R.id.editTextFirstName);
        final EditText middle_name = (EditText) findViewById(R.id.editTextMiddleName);
        final EditText last_name = (EditText) findViewById(R.id.editTextLastName);
        final EditText mobile = (EditText) findViewById(R.id.editTextMobile);
        final EditText address = (EditText) findViewById(R.id.editTextAddress);
        final Button next = (Button) findViewById(R.id.buttonNext2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AllInfo.class);
                startActivity(intent);
            }
        });

        final Button search = (Button) findViewById(R.id.buttonSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_first_name = enter_first_name.getText().toString();
                if (search_first_name.length() <= 0) {
                    enter_first_name.setError("Enter First Name!!!");
                } else {
                    if (!db.checkIfInfoExists(search_first_name)) {
                        Toast.makeText(getApplicationContext(), "Info not found!!!", Toast.LENGTH_LONG).show();
                    } else {
                        Info info = db.getInfo(search_first_name);
                        first_name.setText(info.getFirst_name());
                        middle_name.setText(info.getMiddle_name());
                        last_name.setText(info.getLast_name());
                        mobile.setText(info.getMobile());
                        address.setText(info.getAddress());
                    }
                }
            }
        });

        final EditText delete_text = (EditText) findViewById(R.id.editTextsearch2);
        final Button delete = (Button) findViewById(R.id.buttonDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (delete_text.length() <= 0) {
                    delete_text.setError("Empty Field!!!");
                } else {
                    if (!db.checkIfInfoExists(delete_text.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Info not found!!!", Toast.LENGTH_LONG).show();
                    } else {
                        db.deleteInfo(delete_text.getText().toString());
                        Toast.makeText(getApplicationContext(), "Info Deleted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check, menu);
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
