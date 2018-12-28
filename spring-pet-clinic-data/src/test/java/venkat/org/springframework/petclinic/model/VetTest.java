package venkat.org.springframework.petclinic.model;

import lombok.val;
import org.junit.Assert;
import org.junit.Test;

public class VetTest extends PersonTest {

    @Test
    public void testShouldConstruct() {
        val vet = new Vet();
        Assert.assertNotNull("Object is not constructed", vet);
    }

}