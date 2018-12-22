package venkat.org.springframework.petclinic.services.map;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import venkat.org.springframework.petclinic.model.Owner;

import java.util.HashMap;
import java.util.Map;


public class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;

    @Before
    public void setUp() {
        Map<Long, Owner> map = new HashMap<>();
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("RamaKrishna");
        owner.setLastName("Reddy");
        map.put(owner.getId(), owner);
        ownerServiceMap = new OwnerServiceMap();
        ownerServiceMap.map = map;
    }

    @After
    public void tearDown() {
        ownerServiceMap = null;
    }

    @Test
    public void insert() {

        Owner owner = new Owner();
        owner.setFirstName("Venkat");
        owner.setLastName("Utla");

        int initialSize = ownerServiceMap.map.size();
        ownerServiceMap.save(owner);
        Assert.assertEquals("Insert is not successful", initialSize + 1, ownerServiceMap.map.size());

    }

    @Test
    public void update() {

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Venkat");
        owner.setLastName("Utla");

        int initialSize = ownerServiceMap.map.size();
        ownerServiceMap.save(owner);
        Assert.assertEquals("Update is not successful", initialSize, ownerServiceMap.map.size());

    }

    @Test
    public void findAll() {
        int mapSize = ownerServiceMap.findAll().size();
        Assert.assertEquals("findAll test is failed", 1, mapSize);
    }

    @Test
    public void findById() {
        Owner owner = ownerServiceMap.findById(1L);
        Assert.assertNotNull("FindById is failed", owner);
        Assert.assertEquals("Owner Id not matched in findById", 1L, owner.getId().longValue());
    }

    @Test
    public void delete() {
        int initialSize = ownerServiceMap.map.size();
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("RamaKrishna");
        owner.setLastName("Reddy");
        ownerServiceMap.delete(owner);
        int sizeAfterDelete = ownerServiceMap.map.size();

        Assert.assertEquals("Delete is failed", initialSize - 1, sizeAfterDelete);

    }

    @Test
    public void deleteById() {
        int initialSize = ownerServiceMap.map.size();
        ownerServiceMap.deleteById(1L);
        int sizeAfterDelete = ownerServiceMap.map.size();
        Assert.assertEquals("Delete by ID is failed", initialSize - 1, sizeAfterDelete);
    }

    @Test
    public void testFindByLastName() {
       Owner owner = ownerServiceMap.findByLastName("Reddy");
       Assert.assertNotNull("Unable to locate the owner",owner);
       Assert.assertThat(owner.getLastName(), Matchers.equalToIgnoringCase("Reddy"));
    }

    @Test(expected = RuntimeException.class)
    public void testNullObjectSave() {
        Owner owner = null;
        ownerServiceMap.save(owner);
    }

    @Test
    public void testEmptyMapReturnsExpectedSequence() {
        ownerServiceMap.map = new HashMap<Long,Owner>();

        Owner owner = new Owner();
        owner.setFirstName("Venkat");
        owner.setLastName("Utla");
       Assert.assertEquals("Empty Map did not return correct sequence",1, ownerServiceMap.save(owner).getId().intValue());

    }

}