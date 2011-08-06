package de.zilchinger.vatsimdatamanager.parser;


import de.zilchinger.vatsimdatamanager.VatsimProgressUpdate;
import de.zilchinger.vatsimdatamanager.model.VatsimStatus;

public class StatusParser extends AVatsimParser {

	private static final String DATA_URL_TOKEN ="url0";
	private static final String SERVER_URL_TOKEN ="url1";
	private VatsimStatus vatsimStatus;

	public StatusParser(String source, VatsimProgressUpdate progress) {
		super(source, progress);
		this.vatsimStatus = new VatsimStatus();
		parse();
	}

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
	
	public VatsimStatus getVatsimStatus() {
		return vatsimStatus;
	}

}
