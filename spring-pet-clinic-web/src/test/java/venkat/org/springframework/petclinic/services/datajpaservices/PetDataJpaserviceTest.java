package venkat.org.springframework.petclinic.services.datajpaservices;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.PetType;
import venkat.org.springframework.petclinic.services.PetService;

import java.time.LocalDate;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetDataJpaserviceTest {

    @Autowired
    @Qualifier("petServiceJpa")
    private PetService petService;

    private Pet testPet;

    @Before
    public void setUp() {
        PetType dog = new PetType("DOG");
        dog.setId(1L);
        Owner owner = new Owner("HIG-68,9th Phase, KPHB Colony", "Hyderabad","9100912536");
        owner.setId(1L);
        Pet pet = new Pet("JohnnyTest", dog,owner, LocalDate.now());
        testPet = petService.save(pet);
    }

    @After
    public void tearDown(){
        if(petService.findById(testPet.getId()) != null) {
            petService.deleteById(testPet.getId());
        }
        testPet = null;
    }

    @Test
    public void findAll() {
       Set<Pet> petSet = petService.findAll();
       Assert.assertNotNull(petSet);
       Assert.assertTrue(petSet.size() >= 1);
    }

    @Test
    public void findById() {
        Pet pet = petService.findById(testPet.getId());
        Assert.assertNotNull(pet);
        Assert.assertEquals(testPet.getId(),pet.getId());
    }

    @Test
    public void findById_Invalid() {
        Pet pet = petService.findById(1234L);
        Assert.assertNull(pet);
    }

    @Test
    public void save() {
        PetType dog = new PetType("DOG");
        dog.setId(1L);
        Owner owner = new Owner("HIG-68,9th Phase, KPHB Colony", "Hyderabad","9100912536");
        owner.setId(1L);
        owner.setFirstName("Venkat");
        owner.setLastName("Utla");
        Pet pet = new Pet("JohnnyTest", dog,owner, LocalDate.now());
        Pet savedPet = petService.save(pet);
        Assert.assertNotNull(savedPet);
        Assert.assertNotNull(savedPet.getId());
        Assert.assertEquals(1L, savedPet.getOwner().getId().longValue());
        Assert.assertEquals(1L,savedPet.getPetType().getId().longValue());
    }

    @Test
    @Ignore
    public void delete() {
        petService.delete(testPet);
        Assert.assertNull(petService.findById(testPet.getId()));
    }

    @Test
    public void deleteById() {
        petService.deleteById(testPet.getId());
        Assert.assertNull(petService.findById(testPet.getId()));
    }
}