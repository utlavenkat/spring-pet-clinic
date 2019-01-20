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
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OwnerControllerTest {

    private static final String VIEW_NAME_OWNER_LIST = "owners/index";
    private static final String VIEW_NAME_OWNER_DETAILS = "owners/ownerDetails";
    private static final String VIEW_NAME_FIND_OWNERS = "owners/findOwners";


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
    public void displayOwner() throws Exception {
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

    @Test
    public void findOwnerForm() throws Exception {
        //when
        ResultActions resultActions = mockMvc.perform(get("/owners/findForm"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_FIND_OWNERS))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner",hasProperty("id",is(nullValue()))));

    }

    @Test
    public void findOwnerByLastName_SingleMatch() throws Exception {
        //Given
        final String lastName = "Utla";
        val owner1 = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner1.setFirstName("VenkatTest1");
        owner1.setLastName(lastName);
        owner1.setId(1L);
        Set<Owner> owners = new HashSet<>(1);
        owners.add(owner1);
        when(ownerService.findByLastNameLike(anyString())).thenReturn(owners);

        //when
        ResultActions resultActions = mockMvc.perform(get("/owners/find").param("lastName",lastName));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_OWNER_DETAILS))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner",hasProperty("lastName",equalToIgnoringCase(lastName))));
        verify(ownerService,times(1)).findByLastNameLike(anyString());
    }

    @Test
    public void findOwnerByLastName_MultipleMatch() throws Exception {
        //Given
        final String lastName = "Utla";
        val owner1 = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner1.setFirstName("VenkatTest1");
        owner1.setLastName(lastName);
        owner1.setId(1L);

        val owner2 = new Owner("HIG-68,KPHB","Hyderabad","9100912537");
        owner2.setFirstName("VenkatTest2");
        owner2.setLastName(lastName);
        owner2.setId(2L);

        Set<Owner> owners = new HashSet<>(2);
        owners.add(owner1);
        owners.add(owner2);

        when(ownerService.findByLastNameLike(anyString())).thenReturn(owners);

        //when
        ResultActions resultActions = mockMvc.perform(get("/owners/find").param("lastName",lastName));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_OWNER_LIST))
                .andExpect(model().attributeExists("owners"))
                .andExpect(model().attribute("owners",hasSize(2)));
        verify(ownerService,times(1)).findByLastNameLike(anyString());
    }

    @Test
    public void findOwnerByLastName_NoMatch() throws Exception {
        //Given
        final String lastName = "Utla";

        when(ownerService.findByLastNameLike(anyString())).thenReturn(new HashSet<>());

        //when
        ResultActions resultActions = mockMvc.perform(get("/owners/find").param("lastName",lastName));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_FIND_OWNERS))
                .andExpect(model().attributeDoesNotExist("owners"));
        verify(ownerService,times(1)).findByLastNameLike(anyString());
    }
}