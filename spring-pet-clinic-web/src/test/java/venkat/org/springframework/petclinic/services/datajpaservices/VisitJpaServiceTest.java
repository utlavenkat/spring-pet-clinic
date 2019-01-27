package venkat.org.springframework.petclinic.services.datajpaservices;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.Visit;
import venkat.org.springframework.petclinic.services.VisitService;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitJpaServiceTest{
    @Autowired
    private VisitService visitService;

    private Visit testVisit;

    @Before
    public void setUp()  {
        Pet pet = new Pet();
        pet.setId(1L);
        Visit visit = new Visit(LocalDate.now(), "Casual Visit", pet);
        testVisit =visitService.save(visit);
    }

    @After
    public void tearDown() {
        if(visitService.findById(testVisit.getId()) != null) {
            visitService.deleteById(testVisit.getId());
        }
        testVisit = null;
    }

    @Test
    public void findAll() {
        Set<Visit> visits =visitService.findAll();
        assertNotNull(visits);
        visits.size();
        assertTrue(true);
    }

    @Test
    public void findById() {
        Visit visit = visitService.findById(testVisit.getId());
        assertNotNull(visit);
        assertEquals(testVisit.getId(),visit.getId());
    }

    @Test
    public void findById_Invalid() {
        Visit visit = visitService.findById(12345L);
        assertNull(visit);
    }

    @Test
    public void save() {
        Pet pet = new Pet();
        pet.setId(1L);
        Visit visit = new Visit(LocalDate.now(), "Test Visit", pet);
        Visit savedVisit = visitService.save(visit);
        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getId());
    }

    @Test
    @Ignore
    public void delete() {
        assertNotNull(visitService.findById(testVisit.getId()));
        visitService.delete(testVisit);
        assertNull(visitService.findById(testVisit.getId()));
    }

    @Test
    public void deleteById() {
        assertNotNull(visitService.findById(testVisit.getId()));
        visitService.deleteById(testVisit.getId());
        assertNull(visitService.findById(testVisit.getId()));
    }
    @Test(expected = RuntimeException.class)
    public void deleteById_Invalid() {
        visitService.deleteById(12345L);
    }
}