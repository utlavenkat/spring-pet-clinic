package venkat.org.springframework.petclinic.model;

import org.junit.Assert;
import org.junit.Test;


public class BaseEntityTest {

    @Test
    public void testShouldConstructUsingDefaultConstructor() {
        BaseEntity baseEntity = new BaseEntity();
        Assert.assertNotNull("Base Entity is null", baseEntity);
    }

    @Test
    public void testShouldConstructUsingId() {
        BaseEntity baseEntity = new BaseEntity(1L);
        Assert.assertNotNull("Base Entity is null", baseEntity);
        Assert.assertEquals("ID is not matching", 1L, baseEntity.getId().longValue());
    }

    @Test
    public void testSettersAndGettersForId() {
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(1L);

        Assert.assertEquals("Id is not matching when using setter", 1L, baseEntity.getId().longValue());
    }

}