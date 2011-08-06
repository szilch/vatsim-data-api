package de.zilchinger.vatsimdatamanager;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import de.zilchinger.vatsimdataapi.VatsimDataManager;
import de.zilchinger.vatsimdataapi.VatsimDataUpdate;
import de.zilchinger.vatsimdataapi.VatsimProgressUpdate;
import de.zilchinger.vatsimdataapi.exception.VatsimDataManagerException;
import de.zilchinger.vatsimdataapi.model.VatsimAirport;
import de.zilchinger.vatsimdataapi.model.VatsimController;
import de.zilchinger.vatsimdataapi.model.VatsimPilot;
import de.zilchinger.vatsimdataapi.model.VatsimServer;

public class VatsimDataManagerTest {

    private static final String VATSIM_STATUS_URL = "http://usa-s1.vatsim.net/data/status.txt";

    @Test
    public void testVatsimDataManger() throws VatsimDataManagerException {
        VatsimDataManager manager = new VatsimDataManager();
        manager.init(VATSIM_STATUS_URL);
        manager.addVatsimDataUpdateListener(new VatsimDataUpdate() {

            public void update(ArrayList<VatsimPilot> pilots, ArrayList<VatsimServer> servers, ArrayList<VatsimController> controller,
                    ArrayList<VatsimAirport> airports) {
                Assert.assertNotNull(servers);
                Assert.assertNotNull(pilots);
                Assert.assertNotNull(controller);
            }
        });
        manager.update(new VatsimProgressUpdate() {

            public void updateProgress(int progress, int state) {
            }
        });
    }
}
