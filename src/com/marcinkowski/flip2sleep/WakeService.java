package com.marcinkowski.flip2sleep;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

/**
 * The Class WakeService.
 */
abstract public class WakeService extends IntentService {
	
	/** The Constant LOCK_NAME_STATIC. */
	public static final String LOCK_NAME_STATIC = "com.commonsware.android.syssvc.AppService.Static";

	/** The lock static. */
	private static PowerManager.WakeLock lockStatic = null;

	/**
	 * Acquire static lock.
	 *
	 * @param context the context
	 */
	public static void acquireStaticLock(Context context) {
		getLock(context).acquire();
	}

	/**
	 * Gets the lock.
	 *
	 * @param context the context
	 * @return the lock
	 */
	synchronized private static PowerManager.WakeLock getLock(Context context) {
		if (lockStatic == null) {
			PowerManager mgr = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);

			lockStatic = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
					LOCK_NAME_STATIC);
			lockStatic.setReferenceCounted(true);
		}

		return (lockStatic);
	}

	/**
	 * Instantiates a new wake service.
	 *
	 * @param name the name
	 */
	public WakeService(String name) {
		super(name);
	}

	/**
	 * Do wakeful work.
	 *
	 * @param intent the intent
	 */
	abstract void doWakefulWork(Intent intent);

	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	final protected void onHandleIntent(Intent intent) {
		try {
			doWakefulWork(intent);
		} finally {
			getLock(this).release();
		}
	}
}
