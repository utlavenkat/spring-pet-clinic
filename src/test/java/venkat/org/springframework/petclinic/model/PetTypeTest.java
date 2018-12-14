package venkat.org.springframework.petclinic.model;

import org.junit.Assert;
import org.junit.Test;

public class PetTypeTest {

    @Test
    public void testShouldConstructByDefaultConstructor() {
        PetType petType = new PetType();
        Assert.assertNotNull("Pet Type is null", petType);
    }

    @Test
    public void testShouldConstructByConstructorArgument() {
        PetType petType = new PetType("Tommy");
        Assert.assertNotNull("Pet Type is null", petType);
        Assert.assertEquals("Pet Name not matched", "Tommy", petType.getName());
    }

    @Test
    public void testGettersAndSetters() {
        PetType petType = new PetType();
        petType.setName("Johnny");
        Assert.assertEquals("Pet Name is not matching", "Johnny", petType.getName());
    }
}