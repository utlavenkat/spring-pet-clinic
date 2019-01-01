package venkat.org.springframework.petclinic.services.datajpaservices;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.repositories.OwnerRepository;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class OwnerDataJpaMockitoTest {

    private OwnerDataJpaService ownerDataJpaService;

    @Mock
    private OwnerRepository ownerRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ownerDataJpaService = new OwnerDataJpaService(ownerRepository);
    }

    @Test
    public void testFindAll() {
        val mockedOwners = new HashSet<Owner>();
        val owner1 = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner1.setFirstName("VenkatTest1");
        owner1.setLastName(("UtlaTest1"));
        owner1.setId(1L);

        val owner2 = new Owner("HIG-68,KPHB","Hyderabad","9100912537");
        owner2.setFirstName("VenkatTest2");
        owner2.setLastName(("UtlaTest2"));
        owner2.setId(2L);

        mockedOwners.add(owner1);
        mockedOwners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(mockedOwners);
        val owners = ownerDataJpaService.findAll();
        assertEquals(2, owners.size());
    }

    @Test
    public void testByById_Valid() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(new Owner()));
        val owner = ownerDataJpaService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    public void testByById_InValid() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        val owner = ownerDataJpaService.findById(1L);
        assertNull(owner);
    }
}
