package venkat.org.springframework.petclinic.controllers;

import org.junit.Assert;
import org.junit.Test;


public class VetControllerTest {

    @Test
    public void listVets() {
        VetController vetController = new VetController();
        Assert.assertEquals("Vet List view name is not matching", "vets/index", vetController.listVets());
    }
}