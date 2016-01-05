package com.gezelbom.app61;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Alex
 */
public class CallLogAdapter extends SimpleCursorAdapter {

    /**
     * Experimenting with an enum type to replace a Switch case
     */
    enum CallType {

        INCOMING(CallLog.Calls.INCOMING_TYPE),
        MISSED(CallLog.Calls.MISSED_TYPE),
        OUTGOING(CallLog.Calls.OUTGOING_TYPE);

        int number;

        CallType(int number) {
            this.number = number;
        }

    }

    private Context context;
    private int layout;
    private String[] from;
    private int[] to;

    public CallLogAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context = context;
        this.layout = layout;
        this.to = to;
        this.from = from;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int typeColumn = cursor.getColumnIndex(CallLog.Calls.TYPE);

        for (int i = 0; i < to.length; i++) {
            String text;
            final View v = view.findViewById(to[i]);
            if (i == typeColumn) {
                //Get the callType String, use the fact that the int is in order of the index of the enum CallType
                text = CallType.values()[(Integer.parseInt(cursor.getString(typeColumn))-1)].name();
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
