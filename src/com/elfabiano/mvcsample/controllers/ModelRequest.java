package com.elfabiano.mvcsample.controllers;

import com.elfabiano.mvcsample.model.ValueCount;

public abstract class ModelRequest {
	protected ValueCount model;
	
	public ModelRequest(ValueCount model) {
		this.model = model;
	}
	
	public abstract void make();	
}
