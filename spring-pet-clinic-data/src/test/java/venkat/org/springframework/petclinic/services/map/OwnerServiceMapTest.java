package venkat.org.springframework.petclinic.services.map;

import lombok.val;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.PetType;

import java.time.LocalDate;
import java.util.HashMap;


public class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;

    @Before
    public void setUp() {
        val map = new HashMap<Long,Owner>();
        val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setId(1L);
        owner.setFirstName("RamaKrishna");
        owner.setLastName("Reddy");
        map.put(owner.getId(), owner);

        val petService = new PetServiceMap();
        val petTypeService = new PetTypeServiceMap();
        ownerServiceMap = new OwnerServiceMap(petTypeService,petService);
        ownerServiceMap.map = map;
    }

    @After
    public void tearDown() {
        ownerServiceMap = null;
    }

    @Test
    public void insert() {

        val dog = new PetType("Dog");


        val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setFirstName("Venkat");
        owner.setLastName("Utla");

        val tommy = new Pet("Tommy",dog,owner, LocalDate.now());

        owner.getPets().add(tommy);
        tommy.setOwner(owner);

        val initialSize = ownerServiceMap.map.size();
        val savedOwner =  ownerServiceMap.save(owner);
        Assert.assertEquals("Insert is not successful", initialSize + 1, ownerServiceMap.map.size());
        Assert.assertNotNull("Owner ID is not generated",savedOwner.getId());

        val savedPets = new Pet[savedOwner.getPets().size()];
        savedOwner.getPets().toArray(savedPets);
        val savedPet = savedPets[0];
        Assert.assertNotNull("Pet ID is not generated", savedPet.getId());
        Assert.assertNotNull("Pet ID is not generated", savedPet.getPetType().getId());

    }

    @Test
    public void update() {

        val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setId(1L);
        owner.setFirstName("Venkat");
        owner.setLastName("Utla");

        val initialSize = ownerServiceMap.map.size();
        ownerServiceMap.save(owner);
        Assert.assertEquals("Update is not successful", initialSize, ownerServiceMap.map.size());

    }

    @Test
    public void findAll() {
        val mapSize = ownerServiceMap.findAll().size();
        Assert.assertEquals("findAll test is failed", 1, mapSize);
    }

    @Test
    public void findById() {
        val owner = ownerServiceMap.findById(1L);
        Assert.assertNotNull("FindById is failed", owner);
        Assert.assertEquals("Owner Id not matched in findById", 1L, owner.getId().longValue());
    }

    @Test
    public void delete() {
        val initialSize = ownerServiceMap.map.size();
        val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setId(1L);
        owner.setFirstName("RamaKrishna");
        owner.setLastName("Reddy");
        ownerServiceMap.delete(owner);
        val sizeAfterDelete = ownerServiceMap.map.size();

        Assert.assertEquals("Delete is failed", initialSize - 1, sizeAfterDelete);

    }

    @Test
    public void deleteById() {
        val initialSize = ownerServiceMap.map.size();
        ownerServiceMap.deleteById(1L);
        val sizeAfterDelete = ownerServiceMap.map.size();
        Assert.assertEquals("Delete by ID is failed", initialSize - 1, sizeAfterDelete);
    }

    @Test
    public void testFindByLastName() {
       val owner = ownerServiceMap.findByLastName("Reddy");
       Assert.assertNotNull("Unable to locate the owner",owner);
       Assert.assertThat(owner.getLastName(), Matchers.equalToIgnoringCase("Reddy"));
    }

    @Test(expected = RuntimeException.class)
    public void testNullObjectSave() {
        ownerServiceMap.save(null);
    }

    @Test
    public void testEmptyMapReturnsExpectedSequence() {
        ownerServiceMap.map = new HashMap<Long,Owner>();

        val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setFirstName("Venkat");
        owner.setLastName("Utla");
       Assert.assertEquals("Empty Map did not return correct sequence",1, ownerServiceMap.save(owner).getId().intValue());

    }

    @Test(expected = RuntimeException.class)
    public void testSaveNullOwner() {
        ownerServiceMap.save(null);
    }

    @Test(expected = RuntimeException.class)
    public void testSaveNullPetType() {
        val tommy = new Pet("Tommy",null,null, LocalDate.now());

        val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setFirstName("Venkat");
        owner.setLastName("Utla");
        owner.setTelephone("9100912536");
        owner.setAddress("HIG-68,KPHB");
        owner.setAddress("Hyderabad");
        owner.getPets().add(tommy);
        tommy.setOwner(owner);

        val initialSize = ownerServiceMap.map.size();
        val savedOwner =  ownerServiceMap.save(owner);
        ownerServiceMap.save(null);
    }

}