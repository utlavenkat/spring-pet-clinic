package venkat.org.springframework.petclinic.model;

import lombok.val;
import org.junit.Assert;
import org.junit.Test;

public class PetTypeTest {

    @Test
    public void testShouldConstructByDefaultConstructor() {
        val petType = new PetType();
        Assert.assertNotNull("Pet Type is null", petType);
    }

    @Test
    public void testShouldConstructByConstructorArgument() {
        val petType = new PetType("Tommy");
        Assert.assertNotNull("Pet Type is null", petType);
        Assert.assertEquals("Pet Name not matched", "Tommy", petType.getName());
    }

    @Test
    public void testGettersAndSetters() {
        val petType = new PetType();
        petType.setName("Johnny");
        Assert.assertEquals("Pet Name is not matching", "Johnny", petType.getName());
    }
}