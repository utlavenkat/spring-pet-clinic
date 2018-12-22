package venkat.org.springframework.petclinic.model;

import org.junit.Assert;
import org.junit.Test;

public class SpecialityTest {
    @Test
    public void testShouldConstructDefault() {
        Speciality speciality = new Speciality();
        Assert.assertNotNull("Default Constructor not constructed",speciality);
    }

    @Test
    public void testShouldConstructArgumentConstructor() {
        Speciality speciality = new Speciality("General");
        Assert.assertNotNull("Argument constructor",speciality);
    }

    @Test
    public  void testSettersAndGetters() {
        Speciality speciality = new Speciality();
        speciality.setId(1L);
        speciality.setDescription("General");

        Assert.assertEquals("ID not matching",1, speciality.getId().intValue());
        Assert.assertEquals("Description not matching","General", speciality.getDescription());
    }
}
