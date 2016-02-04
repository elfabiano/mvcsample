package com.elfabiano.mvcsample.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.elfabiano.mvcsample.R;
import com.elfabiano.mvcsample.model.ValueCount;
import com.elfabiano.mvcsample.views.MainView;

public class MainController extends Activity {
	
	private static final String TAG = "MainController";
	
	private ValueCount model;
	private MainView view;
	
	private ModelUpdateThread backgroundThread;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d(TAG, "onCreate");
		
		this.model = ValueCount.getInstance();
		this.view = (MainView)View.inflate(this, R.layout.view_main, null);
		
		this.backgroundThread = new ModelUpdateThread();
		this.backgroundThread.start();
		
		view.setViewListener(viewListener);		
		setContentView(view);
	}
	
	private MainView.ViewListener viewListener = new MainView.ViewListener() {
		@Override
		public void onIncrease() {
			backgroundThread.enqueueRequest(new IncreaseRequest(MainController.this.model));
		}
	};

}
