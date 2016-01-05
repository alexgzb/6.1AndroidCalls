package com.gezelbom.app61;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Simple app that displays the call log in a ListView
 * Clicking on a list item and then on the button Call will start an Intent to call the number
 */
public class MainActivity extends ListActivity {
    private static final String TAG = "MainActivity";

    private TextView callNumber = null;
    private Button callButton = null;
    private ListView list = null;
    private Cursor cursor = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callButton = (Button) findViewById(R.id.buttonCall);
        callNumber = (TextView) findViewById(R.id.textView_number_to_call);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position,
                                    long id) {
                // Move cursor to correct position
                cursor.moveToPosition(position);
                // Get the Number of the listItem and set the Number to the callNumber TextView
                callNumber.setText(cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)));

            }
        });

        getCallLog();

    }

    /**
     * Method that creates a cursor from the calllog and chooses the Number, Type and Date as columns
     * Custom SimpleCursorAdapter is used for the list
     */
    private void getCallLog() {
        cursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);

        String[] fromColumns = {CallLog.Calls.NUMBER, CallLog.Calls.TYPE, CallLog.Calls.DATE};
        int[] toColumns = {R.id.textViewNumberValue, R.id.textViewType, R.id.textViewDate};

        CallLogAdapter adapter = new CallLogAdapter(this, R.layout.call_log_row, cursor, fromColumns, toColumns, 0);
        super.setListAdapter(adapter);

        callButton = (Button) findViewById(R.id.buttonCall);
    }

    /**
     * Use the Number in callNumber textView to start an ACTION_CALL intent
     * @param view
     */
    public void makeCall(View view) {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + callNumber.getText().toString()));
        startActivity(i);
    }

}
