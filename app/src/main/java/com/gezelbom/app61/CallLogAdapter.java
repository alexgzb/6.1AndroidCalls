package com.gezelbom.app61;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alex Gezelbom
 * <p>
 * Custom Adapter to extend SimpleCursorAdapter to manage the values from the cursor
 */
public class CallLogAdapter extends SimpleCursorAdapter {

    /**
     * Experimenting with an enum type to instead of a Switch case
     */
    enum CallType {

        INCOMING(CallLog.Calls.INCOMING_TYPE),
        OUTGOING(CallLog.Calls.OUTGOING_TYPE),
        MISSED(CallLog.Calls.MISSED_TYPE);

        int number;

        CallType(int number) {
            this.number = number;
        }

    }

    private String[] from;
    private int[] to;

    /**
     * Constructor that simply saves the to and from parameters as fields
     */
    public CallLogAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.to = to;
        this.from = from;
    }

    /**
     * Custom bindView to bind the cursor values to the correct view.
     * Custom to manage the special cases of Date and Type since these arent simple Strings
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        final int TYPE = 1;
        final int DATE = 2;
        final int typeColumn = cursor.getColumnIndex(CallLog.Calls.TYPE);

        for (int i = 0; i < to.length; i++) {
            String text;
            final View v = view.findViewById(to[i]);

            if (i == TYPE) {
                // Get the callType String, use the fact that the Constanta in CallLog.Calls are in order of the index of the enum CallType. Using -1 to match them
                text = CallType.values()[cursor.getInt(typeColumn) - 1].toString();
            } else if (i == DATE) {
                // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                text = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(cursor.getLong(cursor.getColumnIndex(from[i]))));
            } else {
                text = cursor.getString(cursor.getColumnIndex(from[i]));
            }
            if (v instanceof TextView) {
                setViewText((TextView) v, text);
            } else if (v instanceof ImageView) {
                setViewImage((ImageView) v, text);
            } else {
                throw new IllegalStateException(v.getClass().getName() + " is not a " +
                        " view that can be bounds by this SimpleCursorAdapter");
            }
        }

    }


}
