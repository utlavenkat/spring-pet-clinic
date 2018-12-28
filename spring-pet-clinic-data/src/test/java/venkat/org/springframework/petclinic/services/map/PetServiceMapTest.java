package venkat.org.springframework.petclinic.services.map;

import lombok.val;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.PersonTest;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.PetType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PetServiceMapTest extends PersonTest {

    PetServiceMap petServiceMap = null;

    @Before
    public void setUp() {
        {
            val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
            owner.setId(1000L);
            owner.setFirstName("RamaKrishna");
            owner.setLastName("Reddy");


            val pet = new Pet();
            pet.setId(1000L);
            pet.setPetType(new PetType("Johnny"));
            pet.setOwner(owner);
            pet.setBirthDate(LocalDate.now());

            val map = new HashMap<>();
            map.put(pet.getId(), pet);

            petServiceMap = new PetServiceMap();
            petServiceMap.map = map;
        }
    }

    @After
    public void tearDown() {
        petServiceMap = null;
    }

    @Test
    public void insert() {
        {

            val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
            owner.setId(2000L);
            owner.setFirstName("Venkat");
            owner.setLastName("Utla");

            val pet = new Pet();
            pet.setPetType(new PetType("Tommy"));
            pet.setOwner(owner);
            pet.setBirthDate(LocalDate.now());

            int initialSize = petServiceMap.map.size();
            petServiceMap.save(pet);
            Assert.assertEquals("Insert is not successful", initialSize + 1, petServiceMap.map.size());

        }
    }

    @Test
    public void update() {
        {

            val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
            owner.setId(1000L);
            owner.setFirstName("RamaKrishna");
            owner.setLastName("Reddy");

            val pet = new Pet();
            pet.setId(1000L);
            pet.setPetType(new PetType("Tommy"));
            pet.setOwner(owner);
            pet.setBirthDate(LocalDate.now());

            int initialSize = petServiceMap.map.size();
            petServiceMap.save(pet);
            Assert.assertEquals("Update is not successful", initialSize, petServiceMap.map.size());

        }
    }

    @Test
    public void findAll() {
        val pet = new Pet();
        pet.setId(2000L);
        pet.setPetType(new PetType("Tommy"));
        pet.setBirthDate(LocalDate.now());
        petServiceMap.map.put(pet.getId(), pet);

        int mapSize = petServiceMap.findAll().size();
        Assert.assertEquals("findAll test is failed", 2, mapSize);
    }

    @Test
    public void findById() {
        val pet = petServiceMap.findById(1000L);
        Assert.assertNotNull("FindById is failed", pet);
        Assert.assertEquals("Owner Id not matched in findById", 1000L, pet.getId().longValue());
    }

    @Test
    public void delete() {
        int initialSize = petServiceMap.map.size();
        val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setId(1000L);
        owner.setFirstName("RamaKrishna");
        owner.setLastName("Reddy");

        val pet = new Pet();
        pet.setId(1000L);
        pet.setPetType(new PetType("Johnny"));
        pet.setOwner(owner);
        pet.setBirthDate(LocalDate.now());

        petServiceMap.delete(pet);
        int sizeAfterDelete = petServiceMap.map.size();

        Assert.assertEquals("Delete is failed", initialSize - 1, sizeAfterDelete);
    }

    @Test
    public void deleteById() {
        int initialSize = petServiceMap.map.size();
        petServiceMap.deleteById(1000L);
        int sizeAfterDelete = petServiceMap.map.size();
        Assert.assertEquals("Delete by ID is failed", initialSize - 1, sizeAfterDelete);
    }
}