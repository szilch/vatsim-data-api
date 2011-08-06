package de.zilchinger.vatsimdataapi.parser;

import de.zilchinger.vatsimdataapi.VatsimProgressUpdate;
import de.zilchinger.vatsimdataapi.model.VatsimStatus;

/**
 * This parser parses the Status file of the VatsimData. This file contains urls where to find the
 * user data and also server data.
 * 
 * @author Stefan Zilch - (06.08.2011)
 */
public class StatusParser extends AVatsimParser {

    /** Tokens... */
    private static final String DATA_URL_TOKEN   = "url0";
    private static final String SERVER_URL_TOKEN = "url1";
    /** vatsimStatus entity */
    private VatsimStatus        vatsimStatus;

    /**
     * Constructor.
     * 
     * @param source
     * @param progress
     */
    public StatusParser(String source, VatsimProgressUpdate progress) {
        super(source, progress);
        this.vatsimStatus = new VatsimStatus();
        parse();
    }

    /** {@inheritDoc} */
    @Override
    public void processLine(String line) {
        if (line.trim().startsWith(";")) {
            return;
        }
        if (line.contains(ERROR_TOKEN)) {
            vatsimStatus = null;
            return;
        }
        String[] result = line.split("=");
        if (result.length == 2) {
            String key = result[0].trim();
            String value = result[1].trim();
            if (key.toLowerCase().contains(DATA_URL_TOKEN)) {
                vatsimStatus.addDataUrl(value);
            }
            if (key.toLowerCase().contains(SERVER_URL_TOKEN)) {
                vatsimStatus.addServerUrl(value);
            }
        }
    }

    /**
     * @return the {@link VatsimStatus}
     */
    public VatsimStatus getVatsimStatus() {
        return vatsimStatus;
    }

}
