package venkat.org.springframework.petclinic.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class VisitTest {

    @Test
    public void testShouldConstructDefault() {
        Visit visit = new Visit();
        Assert.assertNotNull("Object is not constructed",visit);
    }

    @Test
    public void testShouldConstructUsingArgumentConstructor() {
        Pet pet = new Pet();
        Visit visit = new Visit(LocalTime.now(),"Regular Visit",pet);
        Assert.assertNotNull("Object is null", visit);
    }

    @Test
    public void testSettersAndGetters() {
        Pet pet = new Pet();
        LocalTime now = LocalTime.now();
        Visit visit = new Visit();
        visit.setDate(now);
        visit.setDescription("General Visit");
        visit.setId(1L);
        visit.setPet(pet);

        Assert.assertNotNull(visit);
        Assert.assertEquals("ID value not matching", 1,visit.getId().intValue());
        Assert.assertEquals("Description not matching","General Visit",visit.getDescription());
        Assert.assertEquals("Visit Date not matching", now, visit.getDate());
        Assert.assertEquals("Pet value not matching", pet, visit.getPet());
    }
}
