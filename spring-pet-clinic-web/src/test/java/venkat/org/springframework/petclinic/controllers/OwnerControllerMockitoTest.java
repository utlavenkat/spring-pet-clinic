package venkat.org.springframework.petclinic.controllers;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.services.OwnerService;

import java.util.HashSet;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OwnerControllerMockitoTest {

    private OwnerController ownerController;

    @Mock
    private OwnerService ownerService;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new OwnerController(ownerService)).build();
    }

    @Test
    public void testListOwners() throws Exception{
        val mockedOwners = new HashSet<Owner>();
        val owner1 = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner1.setFirstName("VenkatTest1");
        owner1.setLastName(("UtlaTest1"));
        owner1.setId(1L);

        val owner2 = new Owner("HIG-68,KPHB","Hyderabad","9100912537");
        owner2.setFirstName("VenkatTest2");
        owner2.setLastName(("UtlaTest2"));
        owner2.setId(2L);

        mockedOwners.add(owner1);
        mockedOwners.add(owner2);

        when(ownerService.findAll()).thenReturn(mockedOwners);
        mockMvc.perform(get("/owners/index")).andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners",hasSize(2)));
    }
}
