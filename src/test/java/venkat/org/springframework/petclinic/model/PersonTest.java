package venkat.org.springframework.petclinic.model;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    @Test
    public void testShouldConstruct() {
        Person person = new Person("venkat", "utla");
        Assert.assertNotNull(person);
        Assert.assertThat("First Name is not matching", person.getFirstName(), Matchers.equalToIgnoringCase("VENKAT"));
        Assert.assertThat("Last Name is not matching", person.getLastName(), Matchers.equalToIgnoringCase("UTLA"));
    }

    @Test
    public void testSettersAndDefaultConstructor() {
        Person person = new Person();
        person.setFirstName("Venkat");
        person.setLastName("Utla");

        Assert.assertThat("First Name is not match", person.getFirstName(), Matchers.equalToIgnoringCase("Venkat"));
        Assert.assertEquals("Last Name is not matching", "Utla", person.getLastName());
    }

}