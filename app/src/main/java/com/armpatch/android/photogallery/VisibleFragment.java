package com.armpatch.android.photogallery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

public abstract class VisibleFragment extends Fragment {
    public static final String TAG = "VisibleFragment";

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(PollService.ACTION_SHOW_NOTIFICATION);
        getActivity().registerReceiver(mOnShowNotification, filter, PollService.PERM_PRIVATE, null);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(mOnShowNotification);
    }

    private BroadcastReceiver mOnShowNotification = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            /*Toast.makeText(getActivity(),
                    "got a broadcast:" + intent.getAction(),
                    Toast.LENGTH_LONG)
                    .show();*/

            // if we receive this, we're visible, so cancel
            // the notification
            Log.i(TAG, "Cancelling notification");
            setResultCode(Activity.RESULT_CANCELED);
        }
    };
}
