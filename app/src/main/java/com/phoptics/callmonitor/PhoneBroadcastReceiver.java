package com.phoptics.callmonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PhoneBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent service = new Intent(context, PhoneService.class);  
        context.startService(service);
	}

}
