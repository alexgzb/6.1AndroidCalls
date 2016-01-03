package com.gezelbom.app61;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity{
    private static final String TAG = "MainActivity";
    private EditText searchField = null;
    private EditText numberField = null;
    private Button searchButton = null;
    private Button callButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = (Button) findViewById(R.id.buttonSearch);
        callButton = (Button) findViewById(R.id.buttonCall);
        searchField = (EditText) findViewById(R.id.editTextContactName);
        numberField = (EditText) findViewById(R.id.editTextNumber);


    }

    public void makeCall (View view) {
        Intent i = new Intent(Intent.ACTION_CALL);
        String numberString = "tel:" + numberField.getText().toString().trim();
        i.setData(Uri.parse(numberString));
        startActivity(i);
    }

    public void searchContacts (View view) {


    }

}
