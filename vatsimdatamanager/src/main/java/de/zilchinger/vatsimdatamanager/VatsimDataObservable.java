package de.zilchinger.vatsimdatamanager;

interface VatsimDataObservable {

	public void addVatsimDataUpdateListener(VatsimDataUpdate client);
	public void removeVatsimDataUpdateListener(VatsimDataUpdate client);
	public void updateDataListeners();
	
	
}
