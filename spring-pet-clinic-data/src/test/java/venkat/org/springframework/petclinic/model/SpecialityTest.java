package venkat.org.springframework.petclinic.model;

import lombok.val;

import org.junit.Assert;
import org.junit.Test;

public class SpecialityTest {
    @Test
    public void testShouldConstructDefault() {
        val speciality = new Speciality();
        Assert.assertNotNull("Default Constructor not constructed",speciality);
    }

    @Test
    public void testShouldConstructArgumentConstructor() {
        val speciality = new Speciality("General");
        Assert.assertNotNull("Argument constructor",speciality);
    }

    @Test
    public  void testSettersAndGetters() {
        val speciality = new Speciality();
        speciality.setId(1L);
        speciality.setDescription("General");

        Assert.assertEquals("ID not matching",1, speciality.getId().intValue());
        Assert.assertEquals("Description not matching","General", speciality.getDescription());
    }
}
