package de.zilchinger.vatsimdataapi;

interface VatsimDataObservable {

	public void addVatsimDataUpdateListener(VatsimDataUpdate client);
	public void removeVatsimDataUpdateListener(VatsimDataUpdate client);
	public void updateDataListeners();
	
	
}
