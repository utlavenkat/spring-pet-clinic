package venkat.org.springframework.petclinic.services.datajpaservices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.petclinic.model.Speciality;
import venkat.org.springframework.petclinic.services.SpecialityService;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecialityJpaServiceTest {

    @Autowired
    @Qualifier("specialityServiceJpa")
    private SpecialityService specialityService;

    private Speciality testSpeciality;

    @Before
    public void setUp() {
        Speciality speciality = new Speciality("Cardiology");
        testSpeciality = specialityService.save(speciality);
    }

    @After
    public void tearDown() {
        if(specialityService.findById(testSpeciality.getId()) != null) {
            specialityService.deleteById(testSpeciality.getId());
        }
        testSpeciality = null;
    }

    @Test
    public void findAll() {
        Set<Speciality> specialities = specialityService.findAll();
        assertNotNull(specialities);
        assertTrue(specialities.size() >= 1);
    }

    @Test
    public void findById() {
        Speciality speciality = specialityService.findById(testSpeciality.getId());
        assertNotNull(speciality);
        assertEquals(testSpeciality.getId(), speciality.getId());
    }

    @Test
    public void findById_Invalid() {
        Speciality speciality = specialityService.findById(12345L);
        assertNull(speciality);
    }

    @Test
    public void save() {
        Speciality speciality = new Speciality("Test Speciality");
        Speciality savedSpeciality = specialityService.save(speciality);
        assertNotNull(savedSpeciality);
        assertNotNull(savedSpeciality.getId());
        assertEquals(speciality.getDescription(),savedSpeciality.getDescription());
    }

    @Test
    public void delete() {
        assertNotNull(specialityService.findById(testSpeciality.getId()));
        specialityService.delete(testSpeciality);
        assertNull(specialityService.findById(testSpeciality.getId()));
    }

    @Test
    public void deleteById() {
        assertNotNull(specialityService.findById(testSpeciality.getId()));
        specialityService.deleteById(testSpeciality.getId());
        assertNull(specialityService.findById(testSpeciality.getId()));
    }

    @Test(expected = RuntimeException.class)
    public void deleteById_Invalid() {
        specialityService.deleteById(12345L);
    }
}