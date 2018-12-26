package venkat.org.springframework.petclinic.services.datajpaservices;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import venkat.org.springframework.petclinic.model.Speciality;
import venkat.org.springframework.petclinic.repositories.SpecialityRepository;
import venkat.org.springframework.petclinic.services.SpecialityService;

import java.util.HashSet;
import java.util.Set;

@Service
@Qualifier("specialityServiceJpa")
@NoArgsConstructor
@AllArgsConstructor
public class SpecialityJpaService implements SpecialityService {
    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        return  specialityRepository.findById(id).orElse(null);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        specialityRepository.delete(speciality);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.deleteById(id);
    }
}
