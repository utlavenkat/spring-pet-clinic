package venkat.org.springframework.petclinic.services.map;

import lombok.val;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.PetType;
import venkat.org.springframework.petclinic.model.Visit;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.PetTypeService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class VisitServiceMapTest {
    private VisitServiceMap visitServiceMap;
    private Visit testVisit;

    @Before
    public void setUp() {
        val map = new HashMap<>();
        testVisit = new Visit(LocalTime.now(),"General Visit",null);
        testVisit.setId(1L);
        map.put(testVisit.getId(),testVisit);

        PetService petService = new PetServiceMap();
        PetTypeService petTypeService = new PetTypeServiceMap();
        visitServiceMap = new VisitServiceMap(petService,petTypeService);
        visitServiceMap.map = map;

    }

    @After
    public void tearDown() {
        visitServiceMap = null;
    }

    @Test
    public void insert() {
        val petType = new PetType("TestPetType");
        val pet = new Pet("TestPet",petType, new Owner("HIG-68,KPHB","Hyderabad","9100912536"), LocalDate.now());
        val visit = new Visit(LocalTime.now(),"Test Visit",pet);
        int initialSize = visitServiceMap.map.size();
        val savedVisit = visitServiceMap.save(visit);
        Assert.assertEquals("Insert is not successful", initialSize + 1, visitServiceMap.map.size());
        Assert.assertNotNull("Visit ID is not generated",savedVisit.getId());

    }

    @Test
    public void update() {
    }

    @Test
    public void findAll() {
        int mapSize = visitServiceMap.findAll().size();
        Assert.assertEquals("findAll test is failed", 1, mapSize);
    }

    @Test
    public void findById() {
        val visit = visitServiceMap.findById(1L);
        Assert.assertNotNull("FindById is failed", visit);
        Assert.assertEquals("Owner Id not matched in findById", 1L, visit.getId().longValue());
    }

    @Test
    public void delete() {
        int initialSize = visitServiceMap.map.size();
        val visit = new Visit(LocalTime.now(),"General Visit",null);
        visit.setId(1L);
        visitServiceMap.delete(visit);
        int sizeAfterDelete = visitServiceMap.map.size();

        Assert.assertEquals("Delete is failed", initialSize - 1, sizeAfterDelete);

    }

    @Test
    public void deleteById() {
        int initialSize = visitServiceMap.map.size();
        visitServiceMap.deleteById(1L);
        int sizeAfterDelete = visitServiceMap.map.size();
        Assert.assertEquals("Delete by ID is failed", initialSize - 1, sizeAfterDelete);
    }


    @Test(expected = RuntimeException.class)
    public void testNullObjectSave() {
        visitServiceMap.save(null);
    }

    @Test
    public void testEmptyMapReturnsExpectedSequence() {
        visitServiceMap.map = new HashMap<Long,Visit>();
        val petType = new PetType("TestPetType");
        val pet = new Pet("TestPet",petType,new Owner("HIG-68,KPHB","Hyderabad","9100912536"), LocalDate.now());
        val visit = new Visit(LocalTime.now(),"General Visit",pet);
        Assert.assertEquals("Empty Map did not return correct sequence",1, visitServiceMap.save(visit).getId().intValue());

    }

    @Test(expected = RuntimeException.class)
    public void testSaveNullVisit() {
        visitServiceMap.save(null);
    }

    @Test(expected = RuntimeException.class)
    public void testSaveNullPet() {
        val visit = new Visit(LocalTime.now(),"General Visit",null);
        visitServiceMap.save(visit);
    }
}