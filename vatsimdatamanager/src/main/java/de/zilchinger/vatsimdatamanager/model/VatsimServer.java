package de.zilchinger.vatsimdatamanager.model;

import java.io.Serializable;


public class VatsimServer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String indent;
	private String hostname;
	private String location;
	private String name;
	private boolean connectAllowed;
	private GeneralServerData generalData;
	
	
	public VatsimServer(String indent, String hostname, String location,
			String name, boolean connectAllowed, GeneralServerData generalData) {
		super();
		this.indent = indent;
		this.hostname = hostname;
		this.location = location;
		this.name = name;
		this.connectAllowed = connectAllowed;
		this.generalData = generalData;
	}
	
	
	public String getIndent() {
		return indent;
	}
	public void setIndent(String indent) {
		this.indent = indent;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isConnectAllowed() {
		return connectAllowed;
	}
	public void setConnectAllowed(boolean connectAllowed) {
		this.connectAllowed = connectAllowed;
	}


	public GeneralServerData getGeneralData() {
		return generalData;
	}


	public void setGeneralData(GeneralServerData generalData) {
		this.generalData = generalData;
	}
	
	@Override
	public String toString() {
		return this.indent;
	}
	
	
}
