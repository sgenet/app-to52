package com.telefot;

import java.util.Calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TelefotAlert {

	private String reference;
	private long sentDate;
	
	private long startTime;
	private long endTime; 
	private int processingTime;
	
	private String deviceId;
	private double latitude;
	private double longitude;
	private float speed;
	private String address;
	private String heading;
	
	public TelefotAlert(String reference, String deviceId, double latitude, double longitude, float speed, String address, String heading){
		this.setReference(reference);
		this.setDeviceId(deviceId);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setSpeed(speed);
		this.setAddress(address);
		this.setHeading(heading);
		this.sentDate = this.getCurrentDate();
		this.startTime = this.getCurrentDate();
	}
	
	public TelefotAlert() {
		// TODO Auto-generated constructor stub
	}

	/* REFERENCE */
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	/* SENT DATE */
	public long getSentDate() {
		return sentDate;
	}
	public String getFormattedSentDate(){
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		return formatter.format(this.sentDate);
	}
	public void setSentDate(long date) {
		this.sentDate = date;
	}
	public long getCurrentDate(){
		return Calendar.getInstance().getTimeInMillis();
	}
	
	/* PROCESSING TIME */
	public int getProcessingTime() {
		return this.processingTime;
	}
	
	public void setProcessingTime(int time) {
		this.processingTime = time;
	}	
	
	/* DEVICE ID */
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/* LATITUDE */
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/* LONGITUDE */
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/* SPEED */
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/* HEADING */
	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	/* START TIME */
	public void setStartTime(long startTime) {
		this.startTime = this.getCurrentDate();
	}

	public long getStartTime() {
		return startTime;
	}

	/* END TIME */
	public void setEndTime() {
		this.endTime = this.getCurrentDate();
		this.setProcessingTime((int) ((this.getEndTime() - this.getStartTime())/2));
	}

	public long getEndTime() {
		return endTime;
	}

	/* ADDRESS */
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
	
}
