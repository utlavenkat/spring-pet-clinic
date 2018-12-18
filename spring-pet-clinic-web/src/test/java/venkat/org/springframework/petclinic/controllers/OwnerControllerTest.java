package venkat.org.springframework.petclinic.controllers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import venkat.org.springframework.petclinic.services.OwnerService;
import venkat.org.springframework.petclinic.services.map.OwnerServiceMap;

public class OwnerControllerTest {
    private OwnerService ownerService;

    @Before
    public void setup() {
        ownerService = new OwnerServiceMap();
    }

    @After
    public void tearDown() {
        ownerService = null;
    }

    @Test
    public void listOwners() {

        OwnerController ownerController = new OwnerController(ownerService);
        Model model = new ConcurrentModel();
        Assert.assertEquals("View name not matching", "owners/index", ownerController.listOwners(model));
    }
}