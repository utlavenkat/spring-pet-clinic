package venkat.org.springframework.petclinic.services.map;

import lombok.val;
import lombok.var;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import venkat.org.springframework.petclinic.model.PetType;

import java.util.HashMap;
import java.util.Map;


public class PetTypeServiceMapTest{

    private PetTypeServiceMap petTypeServiceMap;

    @Before
    public void setUp() {
        petTypeServiceMap = new PetTypeServiceMap();

        val petType = new PetType("Cat");
        petType.setId(1L);

        val petTypeMap = new HashMap<Long,PetType>();
        petTypeMap.put(petType.getId(),petType);
        petTypeServiceMap.map = petTypeMap;
    }

    @After
    public void tearDown() {
        petTypeServiceMap = null;
    }

    @Test
    public void findAll() {
        Assert.assertEquals("Pet type size not matching",1, petTypeServiceMap.findAll().size());
    }

    @Test
    public void findById() {
        Assert.assertNotNull("Not able to locate pet type",petTypeServiceMap.findById(1L));
    }

    @Test
    public void save() {
        int initialSize = petTypeServiceMap.map.size();
        var petType = new PetType("Dog");
        petType = petTypeServiceMap.save(petType);
        Assert.assertNotNull("Id not generated",petType.getId());
        Assert.assertEquals("ID is not matching",initialSize+ 1,petType.getId().longValue());
    }

    @Test
    public void delete() {
        int initialSize = petTypeServiceMap.map.size();
        val petType = new PetType("Cat");
        petType.setId(1L);
        petTypeServiceMap.delete(petType);
        Assert.assertEquals("Map size not matched",initialSize -1,petTypeServiceMap.map.size());
    }

    @Test
    public void deleteById() {
        int initialSize = petTypeServiceMap.map.size();
        petTypeServiceMap.deleteById(1L);
        Assert.assertEquals("Map size not matched",initialSize -1,petTypeServiceMap.map.size());
    }
}