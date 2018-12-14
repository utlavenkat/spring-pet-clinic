package venkat.org.springframework.petclinic.model;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class OwnerTest {

    @Test
    public void testShouldConstruct() {
        Owner owner = new Owner();
        owner.setFirstName("venkat");
        owner.setLastName("utla");
        Assert.assertNotNull(owner);
        Assert.assertThat("First Name is not matching", owner.getFirstName(), Matchers.equalToIgnoringCase("VENKAT"));
        Assert.assertThat("Last Name is not matching", owner.getLastName(), Matchers.equalToIgnoringCase("UTLA"));
    }

}