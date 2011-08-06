package de.zilchinger.vatsimdataapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class VatsimStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4345191167170889700L;
	private ArrayList<String> serverUrls = new ArrayList<String>();
	private ArrayList<String> dataUrls = new ArrayList<String>();
	private Random rnd = new Random(System.currentTimeMillis());

	public String getRandomServerUrl() {
		if (serverUrls.size() > 0) {
			int index = rnd.nextInt(serverUrls.size());
			return serverUrls.get(index);
		}
		return null;
	}

	public String getRandomDataUrl() {
		if (dataUrls.size() > 0) {
			int index = rnd.nextInt(dataUrls.size());
			return dataUrls.get(index);
		}
		return null;
	}
	
	public void addServerUrl(String url) {
		serverUrls.add(url);
	}
	
	public void addDataUrl(String url) {
		dataUrls.add(url);
	}
}
