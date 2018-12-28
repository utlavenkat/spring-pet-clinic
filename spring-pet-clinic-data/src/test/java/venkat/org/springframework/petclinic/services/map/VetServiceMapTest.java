package venkat.org.springframework.petclinic.services.map;

import lombok.val;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import venkat.org.springframework.petclinic.model.PersonTest;
import venkat.org.springframework.petclinic.model.Speciality;
import venkat.org.springframework.petclinic.model.Vet;
import venkat.org.springframework.petclinic.services.SpecialityService;

import java.util.HashMap;
import java.util.Map;

public class VetServiceMapTest extends PersonTest {
    VetServiceMap vetServiceMap = null;

    @Before
    public void setUp() {
        {
            val specialityService = new SpecialityServiceMap();
            Vet vet = new Vet();
            vet.setId(1000L);
            vet.setFirstName("Lakshmi Narayana");
            vet.setLastName("S");


            val map = new HashMap<>();
            map.put(vet.getId(), vet);

            vetServiceMap = new VetServiceMap(specialityService);
            vetServiceMap.map = map;
        }
    }

    @After
    public void tearDown() {
        vetServiceMap = null;
    }

    @Test
    public void insert() {
        {
            val vet = new Vet();
            vet.setFirstName("Lakshmi Prasad");
            vet.setLastName("B");

            val general = new Speciality("General");
            vet.getSpecialities().add(general);

            int initialSize = vetServiceMap.map.size();
            val savedVet = vetServiceMap.save(vet);
            val savedSpecialities = savedVet.getSpecialities().toArray();
            val savedSpeciality = (Speciality)savedSpecialities[0];
            Assert.assertEquals("Insert is not successful", initialSize + 1, vetServiceMap.map.size());
            Assert.assertNotNull("Id is not set", savedSpeciality.getId());

        }
    }

    @Test
    public void testShouldInsertWithoutSpeciality() {
        {
            val vet = new Vet();
            vet.setFirstName("Lakshmi Prasad");
            vet.setLastName("B");

            int initialSize = vetServiceMap.map.size();
            vetServiceMap.save(vet);

            Assert.assertEquals("Insert is not successful", initialSize + 1, vetServiceMap.map.size());
        }
    }

    @Test
    public void update() {
        {


            val vet = new Vet();
            vet.setId(1000L);
            vet.setFirstName("Lakshmi Prasad");
            vet.setLastName("B");

            int initialSize = vetServiceMap.map.size();
            vetServiceMap.save(vet);
            Assert.assertEquals("Update is not successful", initialSize, vetServiceMap.map.size());

        }
    }

    @Test
    public void findAll() {
        int mapSize = vetServiceMap.findAll().size();
        Assert.assertEquals("findAll test is failed", 1, mapSize);
    }

    @Test
    public void findById() {
        val vet = vetServiceMap.findById(1000L);
        Assert.assertNotNull("FindById is failed", vet);
        Assert.assertEquals("Owner Id not matched in findById", 1000L, vet.getId().longValue());
    }

    @Test
    public void delete() {
        int initialSize = vetServiceMap.map.size();
        val vet = new Vet();
        vet.setId(1000L);
        vet.setFirstName("Lakshmi Narayana");
        vet.setLastName("S");

        vetServiceMap.delete(vet);
        int sizeAfterDelete = vetServiceMap.map.size();

        Assert.assertEquals("Delete is failed", initialSize - 1, sizeAfterDelete);
    }

    @Test
    public void deleteById() {
        int initialSize = vetServiceMap.map.size();
        vetServiceMap.deleteById(1000L);
        int sizeAfterDelete = vetServiceMap.map.size();
        Assert.assertEquals("Delete by ID is failed", initialSize - 1, sizeAfterDelete);
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThowExceptionWhenNullVet(){
        vetServiceMap.save(null);
    }

}