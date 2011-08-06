package de.zilchinger.vatsimdataapi.model;

import java.io.Serializable;


public class FlightplanInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aircraft = "---"; //9
	private String tascruise = "---";//10
	private VatsimAirport departureAirport;//11
	private String altitude = "---";//12
	private VatsimAirport destinationAirport;//13
	private int revision;//15
	private String flightType = "---";//21
	private String departureTime = "---";//22
	private String actualDepartureTime = "---";//23
	private String hrsEnroute = "---";//24
	private String minEnroute = "---";//25
	private String hrsFuel = "---";//26
	private String minFuel = "---";//27
	private String alternativeAirport = "---";//28
	private String remarks = "---";//29
	private String route = "---";//30

	public FlightplanInfo(String[] data) {
		aircraft = data[9];
		tascruise = data[10];
		departureAirport = createAirport(data[11], data[31], data[32]);
		destinationAirport = createAirport(data[13], data[33], data[34]);
		altitude = data[12];
		if (data[15].length() != 0) {
			revision = Integer.parseInt(data[15]);
		}
		flightType = data[21];
		departureTime = data[22];
		actualDepartureTime = data[23];
		hrsEnroute = data[24];
		minEnroute = data[25];
		hrsFuel = data[26];
		minFuel = data[27];
		alternativeAirport = data[28];
		remarks = data[29];
		route = data[30];
	}

	public String getAircraft() {
		return aircraft;
	}
	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
	public String getTascruise() {
		return tascruise;
	}
	public void setTascruise(String tascruise) {
		this.tascruise = tascruise;
	}

	public int getRevision() {
		return revision;
	}
	public void setRevision(int revision) {
		this.revision = revision;
	}
	public String getFlightType() {
		if (flightType.equalsIgnoreCase("v")) {
			return "VFR";
		}
		else if (flightType.equalsIgnoreCase("i")) {
			return "IFR";
		}
		return "---";
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getActualDepartureTime() {
		return actualDepartureTime;
	}
	public void setActualDepartureTime(String actualDepartureTime) {
		this.actualDepartureTime = actualDepartureTime;
	}

	public String getAlternativeAirport() {
		return alternativeAirport;
	}
	public void setAlternativeAirport(String alternativeAirport) {
		this.alternativeAirport = alternativeAirport;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getHrsEnroute() {
		return hrsEnroute;
	}

	public void setHrsEnroute(String hrsEnroute) {
		this.hrsEnroute = hrsEnroute;
	}

	public String getMinEnroute() {
		return minEnroute;
	}

	public void setMinEnroute(String minEnroute) {
		this.minEnroute = minEnroute;
	}

	public String getHrsFuel() {
		return hrsFuel;
	}

	public void setHrsFuel(String hrsFuel) {
		this.hrsFuel = hrsFuel;
	}

	public String getMinFuel() {
		return minFuel;
	}

	public void setMinFuel(String minFuel) {
		this.minFuel = minFuel;
	}
	
	
	
	public VatsimAirport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(VatsimAirport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public VatsimAirport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(VatsimAirport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	private VatsimAirport createAirport(String icao, String lat, String lon) {
		if (icao != null && icao.length() > 0) {
			VatsimAirport ap = new VatsimAirport();
			ap.setIcao(icao);
			try {
				ap.setLatitude((int)(Double.parseDouble(lat) * 1000000));
			} catch (NumberFormatException e) {
				ap.setLatitude(0);
			}
			try {
				ap.setLongitude((int)(Double.parseDouble(lon) * 1000000));
			} catch (NumberFormatException e) {
				ap.setLatitude(0);
			}
			return ap;
		}
		return null;
	}

}
