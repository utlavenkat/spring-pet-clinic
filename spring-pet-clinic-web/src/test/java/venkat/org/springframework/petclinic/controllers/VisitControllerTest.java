package venkat.org.springframework.petclinic.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.Visit;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.VisitService;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class VisitControllerTest {

    private static final String VIEW_NAME_VISIT_FORM = "visits/createOrUpdateVisitForm";

    @Mock
    private PetService petService;

    @Mock
    private VisitService visitService;

    private VisitController visitController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        visitController = new VisitController(visitService, petService);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    public void initNewVisitForm() throws Exception {
        //Given
        final Long petId = 1L;
        Pet pet = new Pet();
        pet.setId(petId);

        when(petService.findById(anyLong())).thenReturn(pet);
        //when
        ResultActions resultActions = mockMvc.perform(get("/owners/*/pets/" + petId + "/visits/new"));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_VISIT_FORM))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attribute("pet", notNullValue()))
                .andExpect(model().attribute("pet", hasProperty("visits", hasSize(1))));

        verify(petService, times(1)).findById(anyLong());

    }

    @Test
    public void processNewVisitForm() throws Exception {
        //Given
        final Long petId = 1L;
        Pet pet = new Pet();
        pet.setId(petId);

        Visit visit = new Visit();
        visit.setId(1L);

        when(petService.findById(anyLong())).thenReturn(pet);
        when(visitService.save(any())).thenReturn(visit);
        //when
        ResultActions resultActions = mockMvc.perform(post("/owners/1/pets/1/visits/new"));

        //then
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(view().name("redirect:/owners/1"));
    }
}