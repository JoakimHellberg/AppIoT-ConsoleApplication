package com.ericsson.appiot.demo.gateway.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import com.google.gson.Gson;

/**
 * AppIoT Console Gateway
 *
 */
public class App 
{
	private static final String HeaderKeyAuthorization 		= "Authorization";
	private static final String HeaderKeyContentType 		= "ContentType";
	private static final String HeaderKeyDatacollectorId 	= "DataCollectorId";
	private static final String HeaderKeyTimestamp 			= "Timestamp";
	private static final String HeaderKeyPayloadType 		= "PayloadType";
	private static final String HeaderValueMeasurements 	= "Measurements";
	private static final String HeaderValueContentType 		= "application/atom+xml;type=entry;charset=utf-8";
	private static final String HeaderValueApplicationJson 	= "application/json";
	
	private static final String RegistrationTicket	 		= "<INSERT REGISTRATION TICKET HERE. PLEASE MAKE SURE TO ESCAPE QUOTES>";
	private static final String SensorId 					= "<INSERT SENSOR ID>";
	
    public static void main( String[] args )
    {
    	App app = new App();
    	
    	// Deserialize registration ticket string
    	RegistrationTicket ticket = new Gson().fromJson(RegistrationTicket, RegistrationTicket.class);
		
		System.out.println("Enter a measurement and press enter. Type exit to shut down application.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = "";
			while(true) {
				input = br.readLine();
				if(input.equalsIgnoreCase("exit")) {
					break;
				}
				
				try {
					double measurement = Double.parseDouble(input);
					app.sendMeasurement(ticket, measurement);
				} catch (NumberFormatException nfe) {
					System.out.println("Can not convert " + input + " to double. Please try again.");
				}				
			}
			System.out.println("System shutting down...");

		} catch (Exception e) {
			System.out.println("Unexpected exception, shutting down...");
		}		
    }		
    
	private void sendMeasurement(RegistrationTicket ticket, double measurement) {
		
    	// Posting to the Event Hub is done to an endpoint with the following format:
    	// https://<namespace>.servicebus.windows.net/<eventhub name>/publishers/<publisher id>/messages
		HttpPost request = new HttpPost(ticket.getOutboxAccessTicket().getHttpServiceUri() + "/"
				+ ticket.getOutboxAccessTicket().getHttpServicePath() + "/messages");

		// Each data collector is uniquely identified by the publisher id in the endpoint address and
		// authenticated using a Shared Access Secret bound to the endpoint address. 
		// this allows for blacklisting down to individual data collector.
		request.addHeader(HeaderKeyAuthorization, ticket.getOutboxAccessTicket().getHttpSas());

		// Set content type accepted by Azure event hub.
		request.addHeader(HeaderKeyContentType, HeaderValueContentType);
		
    	// Sending telemetry data to AppIoT requires the following three headers:
    	// PayloadType: Measurements
    	// DataCollectorId: <The Id of the registered data collector (aka gateway)>
    	// Timestamp: <unix timestamp UTC>
		request.addHeader(HeaderKeyPayloadType, HeaderValueMeasurements);
		request.addHeader(HeaderKeyDatacollectorId, ticket.getDataCollectorId());
		request.addHeader(HeaderKeyTimestamp, Long.toString(System.currentTimeMillis()));
		
		// The payload of the telemetry data may be sent in batches why the following 
		// json structure is required
		// [{ 	"id":"<sensor id>",
		//		"v":[{"t":<unix timestamp utc>,"m":[x,y,n..]},
		// 			 {"t":<unix timestamp utc>,"m":[x,y,n..]}
		//  		]
		// }]
		// "id": The GUID of the sensor that the measurement belongs to. 
		// This value is received from the system when the sensor collection for the sensor is registered.
		// "v": Short for ”Values” and contains the telemetry data for the sensor.
		// "m": Short for "Measurement" and contains the sensor’s measured values. 
		// Please note that this is an array of double. Most common is of course that a sensor produces a single value
		// but an accelerometer would produce measurements for x y z axis.
		// "t": Short for Timestamp and contains the time when the measurement is received to the data collector. 
		// The timestamp is given in the unix timestamp format UTC.
		MeasurementMessage measurementMessage = new MeasurementMessage();
		measurementMessage.setUnixTimestamp(System.currentTimeMillis());
		measurementMessage.setValues(new double[] {measurement});

		// The measurement belong to a single sensor producing a measurement serie. 
		Measurements measurementSerie = new Measurements();
		measurementSerie.setSensorId(SensorId);
		measurementSerie.addMeasurementMessage(measurementMessage);
		
		// Add the measurements serie to the list of measurement series.
		List<Measurements> listOfMeasurementSeries = new Vector<Measurements>();
		listOfMeasurementSeries.add(measurementSerie);
		
		// Send telemetry to AppIoT
		try {

    		String payload = new Gson().toJson(listOfMeasurementSeries);
			StringEntity stringEntity = new StringEntity(payload);
			stringEntity.setContentEncoding(new BasicHeader(HeaderKeyContentType, HeaderValueApplicationJson));
			request.setEntity(stringEntity);

	    	HttpClient httpClient = HttpClientBuilder.create().build();
	    	HttpResponse response = httpClient.execute(request);
	    	int responseCode = response.getStatusLine().getStatusCode();
	    	System.out.println("RESPONSE CODE: " + responseCode);
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
