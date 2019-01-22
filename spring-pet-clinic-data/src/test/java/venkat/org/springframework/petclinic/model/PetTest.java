package venkat.org.springframework.petclinic.model;

import lombok.val;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PetTest extends PersonTest {
    private Owner owner;
    private PetType petType;

    @Before
    public void setup() {
        owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        petType = new PetType();
    }

    @After
    public void tearDown() {
        owner = null;
        petType = null;
    }

    @Test
    public void testShouldConstructUsingDefaultConstruct() {
        val pet = new Pet();
        Assert.assertNotNull(pet);
    }

    @Test
    public void testShouldConstructUsingAllArgConstruct() {
        val localDate = LocalDate.now();
        val pet = new Pet("Tommy",petType, owner, localDate);
        Assert.assertNotNull("Object is null", pet);
        Assert.assertEquals("Owner Object not matched", owner, pet.getOwner());
        Assert.assertEquals("Pet Type not matched", petType, pet.getPetType());
    }

    @Test
    public void testSettersAndGetters() {
        val pet = new Pet();
        val birthDate = LocalDate.now();
        pet.setBirthDate(birthDate);
        pet.setOwner(owner);
        pet.setPetType(petType);

        Assert.assertNotNull("Object is null", pet);
        Assert.assertEquals("Owner Object not matched", owner, pet.getOwner());
        Assert.assertEquals("Pet Type not matched", petType, pet.getPetType());
        Assert.assertEquals("Birth Date not matched", pet.getBirthDate(), birthDate);

    }

    @Test
    public void isNew() {
        final Pet pet = new Pet();
        assertTrue(pet.isNew());
    }

    @Test
    public void isNotNew() {
        final Pet pet = new Pet();
        pet.setId(1L);
        assertFalse(pet.isNew());
    }
}