package venkat.org.springframework.petclinic.services.datajpaservices;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.petclinic.model.PetType;
import venkat.org.springframework.petclinic.services.PetTypeService;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetTypeJpaServiceTest{

    @Autowired
    @Qualifier("petTypeServiceJpa")
    private PetTypeService petTypeService;

    private PetType testPetType;

    @Before
    public void setUp() {
        PetType petType = new PetType("TestPetType");
        testPetType = petTypeService.save(petType);
    }

    @After
    public void tearDown() {
        if(petTypeService.findById(testPetType.getId()) != null) {
            petTypeService.deleteById(testPetType.getId());
        }
        testPetType = null;
    }

    @Test
    public void findAll() {
        Set<PetType> petTypes = petTypeService.findAll();
        Assert.assertNotNull(petTypes);
        Assert.assertTrue(petTypes.size() >= 1);
    }

    @Test
    public void findById() {
        PetType petType =petTypeService.findById(testPetType.getId());
        Assert.assertNotNull(petType);
        Assert.assertNotNull(petType.getId());
    }

    @Test
    public void findById_Invalid() {
        PetType petType =petTypeService.findById(1234L);
        Assert.assertNull(petType);
    }

    @Test
    public void save() {
        PetType petType = new PetType("TestPetType");
        PetType savedPetType = petTypeService.save(petType);
        Assert.assertNotNull(savedPetType);
        Assert.assertNotNull(savedPetType.getId());
        Assert.assertEquals(petType.getName(),savedPetType.getName());
    }

    @Test
    public void delete() {
        Assert.assertNotNull(petTypeService.findById(testPetType.getId()));
        petTypeService.delete(testPetType);
        Assert.assertNull(petTypeService.findById(testPetType.getId()));
    }

    @Test
    public void deleteById() {
        Assert.assertNotNull(petTypeService.findById(testPetType.getId()));
        petTypeService.deleteById(testPetType.getId());
        Assert.assertNull(petTypeService.findById(testPetType.getId()));
    }

    @Test(expected = RuntimeException.class)
    public void deleteById_Invalid() {
        petTypeService.deleteById(1234L);

    }
}