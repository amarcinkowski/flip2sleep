package com.appspot.flip2sleep;

/**
 * The listener interface for receiving orientation events.
 * The class that is interested in processing a orientation
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addOrientationListener<code> method. When
 * the orientation event occurs, that object's appropriate
 * method is invoked.
 *
 * @see OrientationEvent
 */
public interface OrientationListener {
	 
    /**
     * On orientation changed.
     *
     * @param azimuth the azimuth
     * @param pitch the pitch
     * @param roll the roll
     */
    public void onOrientationChanged(float azimuth, 
            float pitch, float roll);
 
    /**
     * Top side of the phone is up
     * The phone is standing on its bottom side.
     */
    public void onTopUp();
 
    /**
     * Bottom side of the phone is up
     * The phone is standing on its top side.
     */
    public void onBottomUp();
 
    /**
     * Right side of the phone is up
     * The phone is standing on its left side.
     */
    public void onRightUp();
 
    /**
     * Left side of the phone is up
     * The phone is standing on its right side.
     */
    public void onLeftUp();
 
    /**
     * screen down.
     */
    public void onFlipped();
}