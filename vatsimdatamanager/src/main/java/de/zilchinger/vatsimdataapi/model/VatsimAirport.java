package de.zilchinger.vatsimdataapi.model;

import java.io.Serializable;

/**
 * POJO of an Airport entity
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public class VatsimAirport implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String            icao;
    private int               latitude;
    private int               longitude;

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((icao == null) ? 0 : icao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        VatsimAirport other = (VatsimAirport) obj;
        if (icao == null) {
            if (other.icao != null) return false;
        } else if (!icao.equals(other.icao)) return false;
        return true;
    }
}
