package com.gezelbom.app61;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ReceiveCall extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        try{
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context, "Phone Is Ringing", Toast.LENGTH_LONG).show();
            }

            if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                Toast.makeText(context, "Call Recieved", Toast.LENGTH_LONG).show();
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context, "Phone Is Idle", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e){e.printStackTrace();}

/*        Log.d("RECEIVER", "ONRECEIVE");
        this.context = context;
        TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        tManager.listen(new PhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);*/

    }

    class PhoneListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    Toast.makeText(context, "CALL_STATE_RINGING", Toast.LENGTH_LONG);
                    Toast.makeText(context,incomingNumber + "is Calling", Toast.LENGTH_LONG);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(context,"CALL_STATE_OFFHOOK",Toast.LENGTH_LONG);
                    Toast.makeText(context,"on a call...",Toast.LENGTH_LONG);
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    Toast.makeText(context,"CALL_STATE_IDLE",Toast.LENGTH_LONG);
                    break;
                default:
                    break;

            }

        }
    }
}
