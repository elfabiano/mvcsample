package com.elfabiano.mvcsample.utils;

import java.util.Observable;
import java.util.Observer;

import android.os.Handler;

public abstract class SynchronizedObserver implements Observer {

	private Handler handler;
	
	public SynchronizedObserver() {	
		this.handler = new Handler();
	}
	
	@Override
	public void update(final Observable observable, final Object data) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				onUpdate(data);
			}
		});		
	}
	
	public abstract void onUpdate(Object data);

}
