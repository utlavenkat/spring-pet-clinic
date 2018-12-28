package venkat.org.springframework.petclinic.model;

import lombok.val;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class OwnerTest {

    @Test
    public void testShouldConstruct() {
        val owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setFirstName("venkat");
        owner.setLastName("utla");
        Assert.assertNotNull(owner);
        Assert.assertThat("First Name is not matching", owner.getFirstName(), Matchers.equalToIgnoringCase("VENKAT"));
        Assert.assertThat("Last Name is not matching", owner.getLastName(), Matchers.equalToIgnoringCase("UTLA"));
    }

}