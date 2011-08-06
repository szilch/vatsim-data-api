package de.zilchinger.vatsimdataapi.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralServerData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6962238643276316446L;
	private static SimpleDateFormat dateInput = new SimpleDateFormat("yyyyMMddHHmmss");
	private int version;
	private int reload;
	private long lastUpdate;
	private int atisAllowMin;
	private int connectedClients;
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getReload() {
		return reload;
	}
	public void setReload(int reload) {
		this.reload = reload;
	}
	public long getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		Date d;
		try {
			d = dateInput.parse(lastUpdate);
			this.lastUpdate = d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public int getAtisAllowMin() {
		return atisAllowMin;
	}
	public void setAtisAllowMin(int atisAllowMin) {
		this.atisAllowMin = atisAllowMin;
	}
	public int getConnectedClients() {
		return connectedClients;
	}
	public void setConnectedClients(int connectedClients) {
		this.connectedClients = connectedClients;
	}

}
