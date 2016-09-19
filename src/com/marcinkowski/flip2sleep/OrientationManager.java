package com.marcinkowski.flip2sleep;

import java.util.List;
 
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
 
/**
 * The Class OrientationManager.
 */
public class OrientationManager {
 
    /** The sensor. */
    private static Sensor sensor;
    
    /** The sensor manager. */
    private static SensorManager sensorManager;
    
    /** The listener. */
    private static OrientationListener listener;
 
    /** indicates whether or not Orientation Sensor is supported. */
    private static Boolean supported;
    
    /** indicates whether or not Orientation Sensor is running. */
    private static boolean running = false;
 
    /**
     * Sides of the phone.
     */
    enum Side {
        
        /** The top. */
        TOP,
        
        /** The bottom. */
        BOTTOM,
        
        /** The left. */
        LEFT,
        
        /** The right. */
        RIGHT,
        
        /** The flipped. */
        FLIPPED;
    }
 
    /**
     * Returns true if the manager is listening to orientation changes.
     *
     * @return true, if is listening
     */
    public static boolean isListening() {
        return running;
    }
 
    /**
     * Unregisters listeners.
     */
    public static void stopListening() {
        running = false;
        try {
            if (sensorManager != null && sensorEventListener != null) {
                sensorManager.unregisterListener(sensorEventListener);
            }
        } catch (Exception e) {}
    }
 
    /**
     * Returns true if at least one Orientation sensor is available.
     *
     * @return true, if is supported
     */
    public static boolean isSupported() {
        if (supported == null) {
            if (OrientationReceiver. getContext() != null) {
                sensorManager = (SensorManager) OrientationReceiver.getContext()
                        .getSystemService(Context.SENSOR_SERVICE);
                List<Sensor> sensors = sensorManager.getSensorList(
                        Sensor.TYPE_ORIENTATION);
                supported = new Boolean(sensors.size() > 0);
            } else {
                supported = Boolean.FALSE;
            }
        }
        return supported;
    }
 
    /**
     * Registers a listener and start listening.
     *
     * @param orientationListener the orientation listener
     */
    public static void startListening(
            OrientationListener orientationListener) {
        sensorManager = (SensorManager) OrientationReceiver.getContext()
                .getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(
                Sensor.TYPE_ORIENTATION);
        if (sensors.size() > 0) {
            sensor = sensors.get(0);
            running = sensorManager.registerListener(
                    sensorEventListener, sensor, 
                    SensorManager.SENSOR_DELAY_NORMAL);
            listener = orientationListener;
        }
    }
 
    /** The listener that listen to events from the orientation listener. */
    private static SensorEventListener sensorEventListener = 
        new SensorEventListener() {
 
        /** The side that is currently up */
        private Side currentSide = null;
        private Side oldSide = null;
        private float azimuth;
        private float pitch;
        private float roll;
 
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
 
        public void onSensorChanged(SensorEvent event) {
 
            azimuth = event.values[0];     // azimuth
            pitch = event.values[1];     // pitch
            roll = event.values[2];        // roll
 
            if (pitch < -45 && pitch > -135) {
                // top side up
                currentSide = Side.TOP;
            } else if (pitch > 45 && pitch < 135) {
                // bottom side up
                currentSide = Side.BOTTOM;
            } else if (roll > 45) {
                // right side up
                currentSide = Side.RIGHT;
            } else if (roll < -45) {
                // left side up
                currentSide = Side.LEFT;
            } else if ((pitch < -176.0 || pitch > 176.0 ) && (roll < 4.0 && roll > -4.0)) {
            	currentSide = Side.FLIPPED;
            }
 
            if (currentSide != null && !currentSide.equals(oldSide)) {
                switch (currentSide) {
                    case TOP : 
                        listener.onTopUp();
                        break;
                    case BOTTOM : 
                        listener.onBottomUp();
                        break;
                    case LEFT: 
                        listener.onLeftUp();
                        break;
                    case RIGHT: 
                        listener.onRightUp();
                        break;
                    case FLIPPED:
                    	listener.onFlipped();
                    	break;
                }
                oldSide = currentSide;
            }
 
            // forwards orientation to the OrientationListener
            listener.onOrientationChanged(azimuth, pitch, roll);
        }
 
    };
 
}