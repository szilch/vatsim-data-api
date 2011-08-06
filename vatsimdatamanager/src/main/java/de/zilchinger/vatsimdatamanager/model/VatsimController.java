package de.zilchinger.vatsimdatamanager.model;

public class VatsimController extends VatsimUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String frequency; //4
	private String facilitytype;//18
	private String visualrange;//19
	private String atisMessage;//35
	
	public VatsimController(String[] data) {
		super(data);
		frequency = data[4];
		facilitytype = data[18];
		visualrange = data[19];
		atisMessage = data[35];
		
	}
	
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public String getAtisMessage() {
		return atisMessage;
	}
	public void setAtisMessage(String atisMessage) {
		this.atisMessage = atisMessage;
	}

	public String getFacilitytype() {
		return facilitytype;
	}

	public void setFacilitytype(String facilitytype) {
		this.facilitytype = facilitytype;
	}

	public String getVisualrange() {
		return visualrange;
	}

	public void setVisualrange(String visualrange) {
		this.visualrange = visualrange;
	}
	
	
}
