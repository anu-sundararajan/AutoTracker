package com.nirmala.android.autotracker;

// http://www.rdcworld-android.blogspot.in/2012/01/get-current-location-coordinates-city.html
// http://stackoverflow.com/questions/1513485/how-do-i-get-the-current-gps-location-programmatically-in-android


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG = "AutoTracker";

    private TextView mTextView;
    private EditText mEditTextPhoneNumber;
    private Button mButtonSendData;
    private Button mButtonSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextView = (TextView)findViewById(R.id.textViewLocation);
        mEditTextPhoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber);
        mButtonSendData = (Button)findViewById(R.id.buttonSendData);
        mButtonSendData.setOnClickListener(this);
        mButtonSaveData = (Button)findViewById(R.id.buttonSaveData);
        mButtonSaveData.setOnClickListener(this);

        setUpSpinner();

        MyLocationService.startService(this);
    }

    private void setUpSpinner() {
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinnerAutoSendFreq);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("No");
        categories.add("Daily");
        categories.add("Weekly");
        categories.add("Monthly");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int freq;
        switch(position) {
            case 0: freq = 0; break;
            case 1: freq = 1; break;
            case 2: freq = 7; break;
            case 3: freq = 30; break;
            default: freq = 0;
        }
        AppData.setAutoSendFreq(this, freq);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onClick(View v) {

        String phoneNo = mEditTextPhoneNumber.getText().toString();

        if (phoneNo.length() > 0) {
            AppData.setPhoneNumber(this, phoneNo);
        } else {
            Toast.makeText(getBaseContext(),
                    "Please enter phone number",
                    Toast.LENGTH_SHORT).show();
        }

        if (v == mButtonSendData) {
            MyMessenger.sendMessage(phoneNo, "Test Msg from Anu. Please ACK.");
        }
    }

    public void updateStatusMessage(String msg) {
        mTextView.setText(msg);
    }
}
