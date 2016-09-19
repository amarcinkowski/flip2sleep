package com.marcinkowski.flip2sleep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * The Class Orientation.
 */
public class OrientationReceiver extends BroadcastReceiver {

	private static Context context;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent arg1) {
		OrientationService.acquireStaticLock(context);
		this.context = context;
		context.startService(new Intent(context, OrientationService.class));
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		OrientationReceiver.context = context;
	}

}