package venkat.org.springframework.petclinic.controllers;

import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.services.OwnerService;

import java.util.HashSet;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OwnerControllerTest {

    private static final String VIEW_NAME_OWNER_LIST = "owners/index";
    private static final String VIEW_NAME_OWNER_DETAILS = "owners/ownerDetails";

    @Mock
    private OwnerService ownerService;

    private OwnerController ownerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ownerController = new OwnerController(ownerService);
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @After
    public void tearDown() {
        ownerService = null;
        ownerController = null;
    }

    @Test
    public void listOwners() throws Exception {
        //Given
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

        // when
        ResultActions resultActions = mockMvc.perform(get("/owners/"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_OWNER_LIST))
                .andExpect(model().attributeExists("owners"));
        verify(ownerService,times(1)).findAll();
    }

    @Test
    public void findOwner() throws Exception {
        //Given
        val owner1 = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner1.setFirstName("VenkatTest1");
        owner1.setLastName(("UtlaTest1"));
        owner1.setId(1L);

        when(ownerService.findById(anyLong())).thenReturn(owner1);

        //when
        ResultActions resultActions = mockMvc.perform(get("/owners/"+owner1.getId()));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_OWNER_DETAILS))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", hasProperty("id",is(owner1.getId()))));
                verify(ownerService,times(1)).findById(anyLong());
    }
}