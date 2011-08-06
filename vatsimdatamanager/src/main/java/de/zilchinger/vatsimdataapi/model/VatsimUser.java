package de.zilchinger.vatsimdataapi.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VatsimUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String callsign;
	private String vId;
	private String realname;
	private int latitude;
	private int longitude;
	private int altitude;
	private long timeLogon;
	protected static SimpleDateFormat dateInput = new SimpleDateFormat("yyyyMMddHHmmss");

	protected VatsimUser(String[] data) {
		callsign = data[0];
		vId = data[1];
		realname = data[2];
		if (data[5].length() !=0) {
			latitude = (int)(Double.parseDouble(data[5]) * 1000000);
		}
		if (data[6].length() != 0) {
			longitude = (int)(Double.parseDouble(data[6]) * 1000000);
		}
		if (data[7].length() != 0) {
			altitude = Integer.parseInt(data[7]);
		}
		Date d;
		try {
			d = dateInput.parse(data[37]);
			timeLogon = d.getTime(); 
		} catch (ParseException e) {
			timeLogon = System.currentTimeMillis();
		}

	}

	public String getCallsign() {
		return callsign;
	}
	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}
	public String getvId() {
		return vId;
	}
	public void setvId(String vId) {
		this.vId = vId;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public long getTimeLogon() {
		return timeLogon;
	}
	public void setTimeLogon(long timeLogon) {
		this.timeLogon = timeLogon;
	}
	
	@Override
	public String toString() {
		return this.callsign;
	}

}
