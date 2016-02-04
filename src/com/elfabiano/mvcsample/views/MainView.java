package com.elfabiano.mvcsample.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elfabiano.mvcsample.R;
import com.elfabiano.mvcsample.model.ValueCount;
import com.elfabiano.mvcsample.utils.SynchronizedObserver;

public class MainView extends RelativeLayout {
	
	private static final String TAG = "MainView";
	
	private Button increaseBtn;
	private TextView number;
	
	private SynchronizedObserver observer;
	
	public interface ViewListener {
		public void onIncrease();
	}
	
	private ViewListener viewListener;
	public void setViewListener(ViewListener l) {
		this.viewListener = l;
	}

	public MainView(Context context, AttributeSet attrs) {
		super(context, attrs);		
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		this.increaseBtn = (Button)findViewById(R.id.button1);
		this.number = (TextView)findViewById(R.id.textView1);
		
		this.observer = new SynchronizedObserver() {
				@Override
				public void onUpdate(Object data) {
					if(data instanceof Integer) {
						bind((Integer) data);
					}
				}
			};
		
		this.increaseBtn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onClick, thread id: " + Thread.currentThread().getId());
				
				viewListener.onIncrease();				
			}
		});
		
		ValueCount.getInstance().addObserver(this.observer);
		
		bind(ValueCount.getInstance().getValue());
	}
	
	public void bind(int value) {
		this.number.setText(Integer.toString(value));
	}
}
