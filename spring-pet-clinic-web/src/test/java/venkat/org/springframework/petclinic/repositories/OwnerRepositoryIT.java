package venkat.org.springframework.petclinic.repositories;


import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.petclinic.model.Owner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OwnerRepositoryIT {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void findByLastNameLike_ExactMatch() {
        //given
        final String lastName = "Utla";
        Iterable<Owner> owners = ownerRepository.findByLastNameContainingIgnoreCase(lastName);
        assertNotNull(owners);
        assertTrue(CollectionUtils.size(owners) == 1);
    }

    @Test
    public void findByLastNameLike_CaseInsensitiveMatch() {
        //given
        final String lastName = "utla";
        Iterable<Owner> owners = ownerRepository.findByLastNameContainingIgnoreCase(lastName);
        assertNotNull(owners);
        assertTrue(CollectionUtils.size(owners) == 1);
    }

    @Test
    public void findByLastNameLike_PartialStringMatch() {
        //given
        final String lastName = "tl";
        Iterable<Owner> owners = ownerRepository.findByLastNameContainingIgnoreCase(lastName);
        assertNotNull(owners);
        assertTrue(CollectionUtils.size(owners) == 1);
    }

    @Test
    public void findByLastNameLike_NoMatch() {
        //given
        final String lastName = "Utla1";
        Iterable<Owner> owners = ownerRepository.findByLastNameContainingIgnoreCase(lastName);
        assertNotNull(owners);
        assertTrue(CollectionUtils.size(owners) == 0);
    }
}
