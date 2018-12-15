package venkat.org.springframework.petclinic.controllers;

import org.junit.Assert;
import org.junit.Test;

public class OwnerControllerTest {

    @Test
    public void listOwners() {
        OwnerController ownerController = new OwnerController();
        Assert.assertEquals("View name not matching", "owners/index", ownerController.listOwners());
    }
}