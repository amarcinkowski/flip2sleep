package com.appspot.flip2sleep;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.Vibrator;

// TODO: Auto-generated Javadoc
/**
 * The Class OrientationService.
 */
public class OrientationService extends Service implements OrientationListener {

	/** The Constant NOTIFY_ID. */
	private final static int NOTIFY_ID = 1;

	/** The m notification manager. */
	private NotificationManager mNotificationManager;
	
	/** The Constant CONTENT_TITLE. */
	private final static CharSequence CONTENT_TITLE = "flip2sleep";
	
	/** The Constant START_TEXT. */
	private final static CharSequence START_TEXT = "Start service";
	
	/** The Constant STOP_TEXT. */
	private final static CharSequence STOP_TEXT = "Stop service!";
	
	/** The context. */
	private Context context;
	
	/** The started. */
	private static boolean started = true;

	/** The notification. */
	Notification notification;

	/** The content intent. */
	PendingIntent contentIntent;

	/**
	 * Mute.
	 */
	private void mute() {
		AudioManager mAudio = (AudioManager) getSystemService(Activity.AUDIO_SERVICE);
		mAudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(100);
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.appspot.flip2sleep.OrientationListener#onBottomUp()
	 */
	@Override
	public void onBottomUp() {
		unmute();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		prepareNotification();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (OrientationManager.isListening()) {
			OrientationManager.stopListening();
		}
	}

	/* (non-Javadoc)
	 * @see com.appspot.flip2sleep.OrientationListener#onFlipped()
	 */
	@Override
	public void onFlipped() {
		mute();
	}

	/* (non-Javadoc)
	 * @see com.appspot.flip2sleep.OrientationListener#onLeftUp()
	 */
	@Override
	public void onLeftUp() {
		unmute();
	}

	/* (non-Javadoc)
	 * @see com.appspot.flip2sleep.OrientationListener#onOrientationChanged(float, float, float)
	 */
	@Override
	public void onOrientationChanged(float azimuth, float pitch, float roll) {
	}

	/* (non-Javadoc)
	 * @see com.appspot.flip2sleep.OrientationListener#onRightUp()
	 */
	@Override
	public void onRightUp() {
		unmute();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStart(android.content.Intent, int)
	 */
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if (OrientationManager.isSupported()) {
			if (started) {
				OrientationManager.startListening(this);
			} else {
				OrientationManager.stopListening();
			}
		}
		showNotification();
	}

	/* (non-Javadoc)
	 * @see com.appspot.flip2sleep.OrientationListener#onTopUp()
	 */
	@Override
	public void onTopUp() {
		unmute();
	}

	/**
	 * Prepare notification.
	 */
	private void prepareNotification() {
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notification = new Notification(R.drawable.notification_icon,
				CONTENT_TITLE, System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, Orientation.class);
		contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
				0);
		context = getApplicationContext();
	}

	/**
	 * Show notification.
	 */
	private void showNotification() {
		if (started) {
			notification.setLatestEventInfo(context, CONTENT_TITLE, STOP_TEXT,
					contentIntent);
		} else {
			notification.setLatestEventInfo(context, CONTENT_TITLE, START_TEXT,
					contentIntent);
		}
		started = !started;
		mNotificationManager.notify(NOTIFY_ID, notification);
	}

	/**
	 * Unmute.
	 */
	private void unmute() {
		AudioManager mAudio = (AudioManager) getSystemService(Activity.AUDIO_SERVICE);
		mAudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	}
}
