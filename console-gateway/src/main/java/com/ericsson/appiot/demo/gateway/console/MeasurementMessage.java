package com.ericsson.appiot.demo.gateway.console;

import com.google.gson.annotations.SerializedName;

public class MeasurementMessage {

	@SerializedName("t")
    private long unixTimestamp;

	@SerializedName("m")
    private double[] values;

	public long getUnixTimestamp() {
		return unixTimestamp;
	}

	public void setUnixTimestamp(long unixTimestamp) {
		this.unixTimestamp = unixTimestamp;
	}

	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}	
}
