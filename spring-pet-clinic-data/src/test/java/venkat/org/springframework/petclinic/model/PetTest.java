package venkat.org.springframework.petclinic.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

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
        Pet pet = new Pet();
        Assert.assertNotNull(pet);
    }

    @Test
    public void testShouldConstructUsingAllArgConstruct() {
        LocalDate localDate = LocalDate.now();
        Pet pet = new Pet("Tommy",petType, owner, localDate);
        Assert.assertNotNull("Object is null", pet);
        Assert.assertEquals("Owner Object not matched", owner, pet.getOwner());
        Assert.assertEquals("Pet Type not matched", petType, pet.getPetType());
    }

    @Test
    public void testSettersAndGetters() {
        Pet pet = new Pet();
        LocalDate birthDate = LocalDate.now();
        pet.setBirthDate(birthDate);
        pet.setOwner(owner);
        pet.setPetType(petType);

        Assert.assertNotNull("Object is null", pet);
        Assert.assertEquals("Owner Object not matched", owner, pet.getOwner());
        Assert.assertEquals("Pet Type not matched", petType, pet.getPetType());
        Assert.assertEquals("Birth Date not matched", pet.getBirthDate(), birthDate);

    }

}