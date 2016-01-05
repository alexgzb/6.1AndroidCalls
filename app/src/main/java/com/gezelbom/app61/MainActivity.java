package com.gezelbom.app61;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends ListActivity {
    private static final String TAG = "MainActivity";

    private Button callButton = null;
    private ListView list = null;
    private Cursor cursor = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);

        String[] fromColumns = {CallLog.Calls.NUMBER, CallLog.Calls.TYPE, CallLog.Calls.DATE};
        int[] toColumns = {R.id.textViewNumberValue, R.id.textViewType, R.id.textViewDate};

        CallLogAdapter adapter = new CallLogAdapter(this, R.layout.call_log_row, cursor, fromColumns, toColumns, 0);
        super.setListAdapter(adapter);

        callButton = (Button) findViewById(R.id.buttonCall);

    }

    public void makeCall(View view) {
        Intent i = new Intent(Intent.ACTION_CALL);
//        String numberString = "tel:" + numberField.getText().toString().trim();
//        i.setData(Uri.parse(numberString));
        startActivity(i);
    }

    public void searchContacts(View view) {


    }

}
