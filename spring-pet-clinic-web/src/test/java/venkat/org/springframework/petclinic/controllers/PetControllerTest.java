package venkat.org.springframework.petclinic.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.PetType;
import venkat.org.springframework.petclinic.services.OwnerService;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.PetTypeService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PetControllerTest {

    private static final String VIEW_NAME_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

    @Mock
    private OwnerService ownerService;

    @Mock
    private PetTypeService petTypeService;

    @Mock
    private PetService petService;

    private PetController petController;

    private MockMvc mockMvc;

    private Owner sampleOwner;

    private Set<PetType> samplePetTypes;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        petController = new PetController(petTypeService, petService, ownerService);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

        sampleOwner = new Owner();
        sampleOwner.setId(1L);

        PetType dog = new PetType();
        dog.setId(1L);
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setId(2L);
        cat.setName("Cat");

        samplePetTypes = new HashSet<>(2);
        samplePetTypes.add(dog);
        samplePetTypes.add(cat);
    }


    @Test
    public void populatePetTypes() {
        when(petTypeService.findAll()).thenReturn(samplePetTypes);
        Collection<PetType> actualPetTypes = petController.populatePetTypes();
        assertNotNull(actualPetTypes);
        assertEquals(samplePetTypes.size(), actualPetTypes.size());
    }

    @Test
    public void populateOwner() {
        when(ownerService.findById(anyLong())).thenReturn(sampleOwner);
        Owner actualOwner = petController.populateOwner(sampleOwner.getId());
        assertNotNull(actualOwner);
        assertEquals(sampleOwner.getId(), actualOwner.getId());
    }

    @Test
    public void createPetForm() throws Exception {
        //Given
        when(petTypeService.findAll()).thenReturn(samplePetTypes);
        when(ownerService.findById(anyLong())).thenReturn(sampleOwner);

        //When
        ResultActions resultActions = mockMvc.perform(get("/owners/" + sampleOwner.getId() + "/pets/new"));

        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_CREATE_OR_UPDATE_PET_FORM))
                .andExpect(model().attributeExists("owner", "types", "pet"))

                .andExpect(model().attribute("owner", notNullValue()))
                .andExpect(model().attribute("owner", hasProperty("id", is(sampleOwner.getId()))))
                .andExpect(model().attribute("pet", hasProperty("id", is(nullValue()))))

                .andExpect(model().attribute("types", notNullValue()))
                .andExpect(model().attribute("types", hasSize(samplePetTypes.size())))

                .andExpect(model().attribute("pet", hasProperty("owner", is(notNullValue()))))
                .andExpect(model().attribute("pet", hasProperty("owner", hasProperty("id"
                        , is(sampleOwner.getId())))));

        verify(petTypeService, times(1)).findAll();
        verify(ownerService, times(1)).findById(anyLong());
        verifyZeroInteractions(petService);

    }

    @Test
    public void createPet() throws Exception {
        //Given
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setOwner(sampleOwner);
        pet.setPetType(samplePetTypes.iterator().next());
        pet.setName("Test Pet");
        pet.setBirthDate(LocalDate.now());

        when(petTypeService.findAll()).thenReturn(samplePetTypes);
        when(ownerService.findById(anyLong())).thenReturn(sampleOwner);
        when(petService.save(any())).thenReturn(pet);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pet);

        //When
        ResultActions resultActions = mockMvc.perform(post("/owners/" + sampleOwner.getId() + "/pets/new")
                .content(jsonInString));

        //Then
        resultActions.andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + sampleOwner.getId()))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", notNullValue()))
                .andExpect(model().attribute("owner", hasProperty("id", is(sampleOwner.getId()))));

        verify(petService, times(1)).save(any());
        verify(ownerService, times(1)).findById(anyLong());
        verify(petTypeService, times(1)).findAll();
    }

    @Test
    public void editPetForm() throws Exception {
        //Given
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setOwner(sampleOwner);
        pet.setPetType(samplePetTypes.iterator().next());
        pet.setName("Test Pet");
        pet.setBirthDate(LocalDate.now());

        when(petTypeService.findAll()).thenReturn(samplePetTypes);
        when(ownerService.findById(anyLong())).thenReturn(sampleOwner);
        when(petService.findById(anyLong())).thenReturn(pet);

        //When
        ResultActions resultActions = mockMvc.perform(get("/owners/" + sampleOwner.getId() + "/pets/" + pet.getId() + "/edit"));

        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_CREATE_OR_UPDATE_PET_FORM))
                .andExpect(model().attributeExists("owner", "types", "pet"))

                .andExpect(model().attribute("owner", notNullValue()))
                .andExpect(model().attribute("owner", hasProperty("id", is(sampleOwner.getId()))))
                .andExpect(model().attribute("pet", hasProperty("id", is(pet.getId()))))

                .andExpect(model().attribute("types", notNullValue()))
                .andExpect(model().attribute("types", hasSize(samplePetTypes.size())))

                .andExpect(model().attribute("pet", hasProperty("owner", is(notNullValue()))))
                .andExpect(model().attribute("pet", hasProperty("owner", hasProperty("id"
                        , is(sampleOwner.getId())))));

        verify(petTypeService, times(1)).findAll();
        verify(ownerService, times(1)).findById(anyLong());
        verify(petService, times(1)).findById(anyLong());

    }

    @Test
    @Ignore
    public void editPet() throws Exception {
        //Given
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setOwner(sampleOwner);
        pet.setPetType(samplePetTypes.iterator().next());
        pet.setName("Test Pet");
        pet.setBirthDate(LocalDate.now());

        when(petTypeService.findAll()).thenReturn(samplePetTypes);
        when(ownerService.findById(anyLong())).thenReturn(sampleOwner);
        when(petService.save(any())).thenReturn(pet);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pet);

        //When
        ResultActions resultActions = mockMvc.perform(post("/owners/" + sampleOwner.getId() + "/pets/" + pet.getId() + "/edit")
                .content(jsonInString));

        //Then
        resultActions.andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + sampleOwner.getId()))
                .andExpect(model().attributeExists("owner"));
        //.andExpect(model().attribute("owner",notNullValue()))
        //.andExpect(model().attribute("owner",hasProperty("id",is(sampleOwner.getId()))));

        // verify(petService,times(1)).save(any());
        //verify(ownerService,times(1)).findById(anyLong());
        //verify(petTypeService,times(1)).findAll();
    }

}