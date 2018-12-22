package venkat.org.springframework.petclinic.services.map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import venkat.org.springframework.petclinic.model.PersonTest;
import venkat.org.springframework.petclinic.model.Speciality;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SpecialityServiceMapTest extends PersonTest {

    private SpecialityServiceMap specialityServiceMap;

    @Before
    public void setUp()  {

        Speciality speciality = new Speciality("General");
        speciality.setId(1L);

        Map<Long,Speciality> specialityMap = new HashMap<>();
        specialityMap.put(speciality.getId(),speciality);

        specialityServiceMap = new SpecialityServiceMap();
        specialityServiceMap.map = specialityMap;
    }

    @After
    public void tearDown() {
        specialityServiceMap = null;
    }

    @Test
    public void findAll() {
        Assert.assertEquals("Specialities size not matching", specialityServiceMap.map.size(), specialityServiceMap.findAll().size());
    }

    @Test
    public void findById() {
        Speciality speciality = specialityServiceMap.findById(1L);
        Assert.assertNotNull("Speciality is null",speciality);
    }

    @Test
    public void save() {
        int initialSize = specialityServiceMap.map.size();
        Speciality speciality = new Speciality("Cardiology");
        speciality = specialityServiceMap.save(speciality);
        Assert.assertNotNull("speciality id is nulll",speciality.getId());
        Assert.assertEquals("speciality not inserted",initialSize+1, specialityServiceMap.map.size());
    }

    @Test
    public void delete() {
        Speciality speciality = new Speciality("General");
        speciality.setId(1L);
        specialityServiceMap.delete(speciality);
        Assert.assertNull("Delete is not successful", specialityServiceMap.map.get(1L));
    }

    @Test
    public void deleteById() {
        specialityServiceMap.deleteById(1L);
        Assert.assertNull("Delete is not successful", specialityServiceMap.map.get(1L));


    }
}