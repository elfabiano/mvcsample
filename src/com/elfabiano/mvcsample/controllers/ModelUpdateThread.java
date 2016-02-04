package com.elfabiano.mvcsample.controllers;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class ModelUpdateThread extends Thread {
	private static final String TAG = ModelUpdateThread.class.getSimpleName();
	
	private Handler handler;
	
	@Override
	public void run() {
		try {
			Looper.prepare();
			handler = new Handler();
			Looper.loop();
		} catch (Throwable t) {
			Log.e(TAG, "Thread halted due to an error", t);
		}
	}
	
	public synchronized void requestStop() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				Looper.myLooper().quit();
			}
		});
	}
	
	public synchronized void enqueueRequest(final ModelRequest request) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				try {
					request.make();
				} finally {}
			}
		});
	}
}
