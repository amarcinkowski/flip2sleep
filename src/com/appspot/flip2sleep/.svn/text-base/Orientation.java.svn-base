package com.appspot.flip2sleep;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * The Class Orientation.
 */
public class Orientation extends Activity {

	/** The context. */
	private static Context CONTEXT;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		startService(new Intent(Orientation.this, OrientationService.class));
		CONTEXT = this;
		finish();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	protected void onResume() {
		super.onResume();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	protected void onDestroy() {
		super.onDestroy();
	}

	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	public static Context getContext() {
		return CONTEXT;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		finish();
	}

}