package com.elfabiano.mvcsample.model;

import java.util.Observable;

import android.util.Log;

@ThreadSafe
public class ValueCount extends Observable {
	private static final String TAG = "ValueCount";
	
	private int value;
	private static ValueCount instance;
	
	private ValueCount() {}
	
	public synchronized static ValueCount getInstance() {
		if(instance == null) {
			instance = new ValueCount();
		}
		
		return instance;
	}

	public synchronized void increase() {
		Log.d(TAG, "increase, thread id:" + Thread.currentThread().getId());

		setValue(value + 1);
	}

	public int getValue() {
		synchronized(this) {
			return value;
		}
	}

	public void setValue(int value) {
		synchronized(this) {
			this.value = value;
			this.setChanged();
		}
			this.notifyObservers(value);
	}
}
