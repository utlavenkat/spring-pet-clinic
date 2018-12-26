package venkat.org.springframework.petclinic.services.datajpaservices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.petclinic.model.Vet;
import venkat.org.springframework.petclinic.services.VetService;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VetJpaServiceTest {

    @Autowired
    @Qualifier("vetJpaService")
    private VetService vetService;

    private Vet testVet;

    @Before
    public void setUp() {
        Vet vet = new Vet();
        vet.setFirstName("Divnesh");
        vet.setLastName("Gopisetty");
        testVet = vetService.save(vet);

    }

    @After
    public void tearDown() {
        if(vetService.findById(testVet.getId()) != null) {
            vetService.deleteById(testVet.getId());
        }
        testVet = null;
    }

    @Test
    public void findAll() {
        Set<Vet> vetSet = vetService.findAll();
        assertNotNull(vetSet);
        assertTrue(vetSet.size() >= 1);
    }

    @Test
    public void findById() {
        Vet vet = vetService.findById(testVet.getId());
        assertNotNull(vet);
        assertNotNull(vet.getId());
        assertEquals(testVet.getId(),vet.getId());
    }
    @Test
    public void findById_Invalid() {
        Vet vet = vetService.findById(12345L);
        assertNull(vet);
    }

    @Test
    public void save() {
        Vet vet = new Vet();
        vet.setFirstName("TestFirstName");
        vet.setLastName("testLastName");
        Vet savedVet = vetService.save(vet);
        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
    }

    @Test
    public void delete() {
        assertNotNull(vetService.findById(testVet.getId()));
        vetService.delete(testVet);
        assertNull(vetService.findById(testVet.getId()));
    }

    @Test
    public void deleteById() {
        assertNotNull(vetService.findById(testVet.getId()));
        vetService.deleteById(testVet.getId());
        assertNull(vetService.findById(testVet.getId()));
    }

    @Test(expected = RuntimeException.class)
    public void deleteById_Invalid() {
        vetService.deleteById(12345L);
    }
}