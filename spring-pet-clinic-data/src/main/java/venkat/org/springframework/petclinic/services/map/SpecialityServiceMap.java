package venkat.org.springframework.petclinic.services.map;

import org.springframework.stereotype.Service;
import venkat.org.springframework.petclinic.model.Speciality;
import venkat.org.springframework.petclinic.services.SpecialityService;

import java.util.Set;

@Service
public class SpecialityServiceMap extends AbstractMapService<Speciality,Long> implements SpecialityService {


    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public Speciality findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
