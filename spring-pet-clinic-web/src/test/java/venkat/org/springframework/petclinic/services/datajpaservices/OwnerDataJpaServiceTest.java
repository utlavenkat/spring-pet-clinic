package venkat.org.springframework.petclinic.services.datajpaservices;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.PetType;

import java.time.LocalDate;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OwnerDataJpaServiceTest {
    @Autowired
    @Qualifier("ownerServiceJpa")
    private OwnerDataJpaService ownerDataJpaService;

    private Owner testOwner;


    @Before
    public  void  setUp() {
        Owner owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setFirstName("VenkatTest");
        owner.setLastName(("UtlaTest"));

        PetType petType = new PetType();
        petType.setName("DOG");
        petType.setId(1L);

        owner.addPet(new Pet("TommyTest", petType, owner, LocalDate.now()));
        testOwner = ownerDataJpaService.save(owner);

    }

    @After
    public void tearDown() {
        if(ownerDataJpaService.findById(testOwner.getId()) != null){
            ownerDataJpaService.deleteById(testOwner.getId());
        }
        testOwner = null;
    }

    @Test
    public void findByLastName_Valid() {
        Owner owner = ownerDataJpaService.findByLastName("UtlaTest");
        Assert.assertNotNull(owner);
        Assert.assertNotNull(owner.getId());
        Assert.assertEquals("UtlaTest",owner.getLastName());
    }

    @Test
    public void findByLastName_InValid() {
        Owner owner = ownerDataJpaService.findByLastName("doesnotexist");
        Assert.assertNull(owner);
    }

    @Test
    public void findAll() {
        Set<Owner> ownerSet = ownerDataJpaService.findAll();
        Assert.assertNotNull(ownerSet);
        Assert.assertTrue(ownerSet.size() >0);
    }

    @Test
    public void findById() {
        Owner owner = ownerDataJpaService.findById(testOwner.getId());
        Assert.assertNotNull(owner);
        Assert.assertEquals(testOwner.getId().intValue(), owner.getId().intValue());
    }

    @Test
    public void save() {
        Owner owner = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner.setFirstName("Venkat");
        owner.setLastName(("Utla"));

        PetType petType = new PetType();
        petType.setName("DOG");
        petType.setId(1L);

        owner.addPet(new Pet("Tommy", petType, owner, LocalDate.now()));
        Owner savedOwner = ownerDataJpaService.save(owner);
        Assert.assertNotNull(savedOwner);
        Assert.assertNotNull(savedOwner.getId());
    }

    @Test
    public void delete() {
        Assert.assertNotNull(ownerDataJpaService.findById(testOwner.getId()));
        ownerDataJpaService.delete(testOwner);
        Assert.assertNull(ownerDataJpaService.findById(testOwner.getId()));

    }

    @Test
    public void deleteById() {
        Assert.assertNotNull(ownerDataJpaService.findById(testOwner.getId()));
        ownerDataJpaService.deleteById(testOwner.getId());
        Assert.assertNull(ownerDataJpaService.findById(testOwner.getId()));
    }

    @Test
    public  void findByLastNameLike_ExactMatch() {
        //Given
        final String lastName = "Chenna";
        //when
        Set<Owner> owners = ownerDataJpaService.findByLastNameLike(lastName);
        //then
        Assert.assertEquals(owners.size(),1);
    }


    @Test
    public  void findByLastNameLike_CaseInsensitiveMatch() {
        //Given
        final String lastName = "chenna";
        //when
        Set<Owner> owners = ownerDataJpaService.findByLastNameLike(lastName);
        //then
        Assert.assertEquals(owners.size(),1);
    }

    @Test
    public  void findByLastNameLike_NoMatch() {
        //Given
        final String lastName = "chenna1";
        //when
        Set<Owner> owners = ownerDataJpaService.findByLastNameLike(lastName);
        //then
        Assert.assertEquals(owners.size(),0);
    }
}