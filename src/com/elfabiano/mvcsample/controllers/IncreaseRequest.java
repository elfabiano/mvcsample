package com.elfabiano.mvcsample.controllers;

import com.elfabiano.mvcsample.model.ValueCount;

public class IncreaseRequest extends ModelRequest {

	public IncreaseRequest(ValueCount model) {
		super(model);
	}
	
	@Override
	public void make() {
		model.increase();
	}	
}
