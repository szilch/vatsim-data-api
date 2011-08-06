package de.zilchinger.vatsimdataapi.model;

/**
 * POJO of a pilot entity
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public class VatsimPilot extends VatsimUser {

    private static final long serialVersionUID = 1L;
    private int               groundspeed;
    private String            transponder;
    private int               heading;
    private String            qnhIHg;
    private String            qnhMb;
    private FlightplanInfo    flightplanInfo   = null;

    public VatsimPilot(String[] data) {
        super(data);
        if (data[8].length() != 0) {
            groundspeed = Integer.parseInt(data[8]);
        }
        transponder = data[17];
        if (data[38].length() != 0) {
            heading = Integer.parseInt(data[38]);
        }
        qnhIHg = data[39];
        qnhMb = data[40];
        flightplanInfo = new FlightplanInfo(data);
    }

    public int getGroundspeed() {
        return groundspeed;
    }

    public void setGroundspeed(int groundspeed) {
        this.groundspeed = groundspeed;
    }

    public String getTransponder() {
        return transponder;
    }

    public void setTransponder(String transponder) {
        this.transponder = transponder;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public String getQnhIHg() {
        return qnhIHg;
    }

    public void setQnhIHg(String qnhIHg) {
        this.qnhIHg = qnhIHg;
    }

    public String getQnhMb() {
        return qnhMb;
    }

    public void setQnhMb(String qnhMb) {
        this.qnhMb = qnhMb;
    }

    public FlightplanInfo getFlightplanInfo() {
        return flightplanInfo;
    }

    public void setFlightplanInfo(FlightplanInfo flightplanInfo) {
        this.flightplanInfo = flightplanInfo;
    }

    @Override
    public String toString() {
        return super.getCallsign();
    }

    public boolean isAirborn() {
        if (this.groundspeed > 50) {
            return true;
        }
        return false;
    }

}
