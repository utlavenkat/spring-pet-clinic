package venkat.org.springframework.petclinic.controllers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import venkat.org.springframework.petclinic.services.VetService;
import venkat.org.springframework.petclinic.services.map.VetServiceMap;


public class VetControllerTest {

    private VetService vetService;

    @Before
    public void setUp() {
        vetService = new VetServiceMap();
    }

    @After
    public void tearDown() {
        vetService = null;
    }

    @Test
    public void listVets() {
        Model model = new ConcurrentModel();
        VetController vetController = new VetController(vetService);
        Assert.assertEquals("Vet List view name is not matching", "vets/index", vetController.listVets(model));
    }
}