package com.ericsson.appiot.demo.gateway.console;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.google.gson.annotations.SerializedName;

public class Measurements {

    public Measurements()
    {
        MeasurementMessages = new LinkedList<MeasurementMessage>();
    }

    @SerializedName("id")
    private  String sensorId;
    
	@SerializedName("v")
    private List<MeasurementMessage> MeasurementMessages = new Vector<MeasurementMessage>();

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public List<MeasurementMessage> getMeasurementMessages() {
		return MeasurementMessages;
	}

	public void setMeasurementMessages(List<MeasurementMessage> measurementMessages) {
		MeasurementMessages = measurementMessages;
	}    
	
	public void addMeasurementMessage(MeasurementMessage measurementMessage) {
		MeasurementMessages.add(measurementMessage);
	}
}
